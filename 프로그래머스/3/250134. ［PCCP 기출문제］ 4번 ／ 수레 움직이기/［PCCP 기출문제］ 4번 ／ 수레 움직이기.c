#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <stdint.h>

typedef struct {
    uint8_t rPos, bPos;     // 0..15
    uint16_t rMask, bMask;  // visited bitmask
    uint16_t dist;          // turns
} Node;

static inline int idx(int i, int j, int cols) { return i * cols + j; }

static inline uint64_t pack_key(uint8_t rPos, uint8_t bPos, uint16_t rMask, uint16_t bMask) {
    // 4 bits rPos | 4 bits bPos | 16 bits rMask | 16 bits bMask  => 40 bits
    return (uint64_t)rPos | ((uint64_t)bPos << 4) | ((uint64_t)rMask << 8) | ((uint64_t)bMask << 24);
}

// splitmix64 hash
static inline uint64_t hash64(uint64_t x) {
    x += 0x9e3779b97f4a7c15ULL;
    x = (x ^ (x >> 30)) * 0xbf58476d1ce4e5b9ULL;
    x = (x ^ (x >> 27)) * 0x94d049bb133111ebULL;
    return x ^ (x >> 31);
}

typedef struct {
    uint64_t *table;
    uint32_t capMask; // cap is power of 2, mask = cap-1
} HashSet;

static HashSet hs_create(uint32_t capPow2) {
    HashSet hs;
    hs.table = (uint64_t*)calloc(capPow2, sizeof(uint64_t));
    hs.capMask = capPow2 - 1;
    return hs;
}

static void hs_free(HashSet *hs) {
    free(hs->table);
    hs->table = NULL;
}

static bool hs_contains_or_insert(HashSet *hs, uint64_t key) {
    // returns true if already existed, else insert and return false
    uint32_t i = (uint32_t)hash64(key) & hs->capMask;
    while (true) {
        uint64_t cur = hs->table[i];
        if (cur == 0ULL) {          // empty
            hs->table[i] = key;
            return false;
        }
        if (cur == key) return true;
        i = (i + 1) & hs->capMask;
    }
}

static inline int manhattan_pos(int p1, int p2, int cols) {
    int x1 = p1 / cols, y1 = p1 % cols;
    int x2 = p2 / cols, y2 = p2 % cols;
    int dx = x1 - x2; if (dx < 0) dx = -dx;
    int dy = y1 - y2; if (dy < 0) dy = -dy;
    return dx + dy;
}

// maze_rows는 2차원 배열 maze의 행 길이, maze_cols는 2차원 배열 maze의 열 길이입니다.
int solution(int** maze, size_t maze_rows, size_t maze_cols) {
    int n = (int)maze_rows, m = (int)maze_cols;
    int Rstart = -1, Bstart = -1, Rgoal = -1, Bgoal = -1;

    // wall check array
    bool wall[16] = {false};

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            int v = maze[i][j];
            int p = idx(i, j, m);
            if (v == 5) wall[p] = true;
            else if (v == 1) Rstart = p;
            else if (v == 2) Bstart = p;
            else if (v == 3) Rgoal  = p;
            else if (v == 4) Bgoal  = p;
        }
    }

    // BFS queue
    size_t qcap = 1 << 18;
    size_t qh = 0, qt = 0;
    Node *q = (Node*)malloc(qcap * sizeof(Node));

    // visited hash set (4,194,304 slots ~ 32MB) - 보통 충분
    HashSet vis = hs_create(1u << 22);

    uint16_t rMask0 = (uint16_t)(1u << Rstart);
    uint16_t bMask0 = (uint16_t)(1u << Bstart);

    Node start = {(uint8_t)Rstart, (uint8_t)Bstart, rMask0, bMask0, 0};
    q[qt++] = start;
    hs_contains_or_insert(&vis, pack_key(start.rPos, start.bPos, start.rMask, start.bMask));

    // directions: up, down, left, right
    const int dx[4] = {-1, 1, 0, 0};
    const int dy[4] = {0, 0, -1, 1};

    while (qh < qt) {
        Node cur = q[qh++];

        if (cur.rPos == (uint8_t)Rgoal && cur.bPos == (uint8_t)Bgoal) {
            hs_free(&vis);
            free(q);
            return (int)cur.dist;
        }

        bool rDone = (cur.rPos == (uint8_t)Rgoal);
        bool bDone = (cur.bPos == (uint8_t)Bgoal);

        // generate possible moves for red
        uint8_t rNextPos[4];
        uint16_t rNextMask[4];
        int rCnt = 0;

        if (rDone) {
            rNextPos[rCnt] = cur.rPos;
            rNextMask[rCnt] = cur.rMask;
            rCnt++;
        } else {
            int rx = cur.rPos / m, ry = cur.rPos % m;
            for (int k = 0; k < 4; k++) {
                int nx = rx + dx[k], ny = ry + dy[k];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                int np = idx(nx, ny, m);
                if (wall[np]) continue;
                if (cur.rMask & (1u << np)) continue; // already visited by red
                rNextPos[rCnt] = (uint8_t)np;
                rNextMask[rCnt] = (uint16_t)(cur.rMask | (1u << np));
                rCnt++;
            }
        }

        // generate possible moves for blue
        uint8_t bNextPos[4];
        uint16_t bNextMask[4];
        int bCnt = 0;

        if (bDone) {
            bNextPos[bCnt] = cur.bPos;
            bNextMask[bCnt] = cur.bMask;
            bCnt++;
        } else {
            int bx = cur.bPos / m, by = cur.bPos % m;
            for (int k = 0; k < 4; k++) {
                int nx = bx + dx[k], ny = by + dy[k];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                int np = idx(nx, ny, m);
                if (wall[np]) continue;
                if (cur.bMask & (1u << np)) continue; // already visited by blue
                bNextPos[bCnt] = (uint8_t)np;
                bNextMask[bCnt] = (uint16_t)(cur.bMask | (1u << np));
                bCnt++;
            }
        }

        // 반드시 움직여야 하는데(도착칸 제외) 선택지가 없으면 dead-end
        if (!rDone && rCnt == 0) continue;
        if (!bDone && bCnt == 0) continue;

        // combine moves with constraints
        for (int i = 0; i < rCnt; i++) {
            for (int j = 0; j < bCnt; j++) {
                uint8_t rn = rNextPos[i], bn = bNextPos[j];

                // same cell
                if (rn == bn) continue;

                // swap (only meaningful when both actually moved)
                bool rMoved = (rn != cur.rPos);
                bool bMoved = (bn != cur.bPos);
                if (rMoved && bMoved && rn == cur.bPos && bn == cur.rPos) continue;

                uint16_t rnm = rNextMask[i], bnm = bNextMask[j];

                uint64_t key = pack_key(rn, bn, rnm, bnm);
                if (hs_contains_or_insert(&vis, key)) continue;

                if (qt == qcap) {
                    qcap <<= 1;
                    q = (Node*)realloc(q, qcap * sizeof(Node));
                }
                q[qt++] = (Node){rn, bn, rnm, bnm, (uint16_t)(cur.dist + 1)};
            }
        }
    }

    hs_free(&vis);
    free(q);
    return 0;
}