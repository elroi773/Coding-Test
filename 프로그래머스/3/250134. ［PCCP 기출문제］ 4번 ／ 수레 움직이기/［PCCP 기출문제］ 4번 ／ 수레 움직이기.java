import java.util.*;

/**
 * 0: empty, 1: red start, 2: blue start, 3: red goal, 4: blue goal, 5: wall
 * n,m <= 4  => positions <= 16, visited masks can be 16-bit
 *
 * BFS state = (rPos, bPos, rMask, bMask, dist)
 * - rMask/bMask: cells visited by each cart (including start)
 * - each turn: carts NOT on their goal must move 1 step (U/D/L/R)
 * - carts on their goal stay fixed
 * - cannot move into wall/outside
 * - cannot revisit own visited cells
 * - cannot land on same cell
 * - cannot swap positions in one turn
 */
class Solution {
    static class Node {
        int rPos, bPos;
        int rMask, bMask;
        int dist;
        Node(int rPos, int bPos, int rMask, int bMask, int dist) {
            this.rPos = rPos; this.bPos = bPos;
            this.rMask = rMask; this.bMask = bMask;
            this.dist = dist;
        }
    }

    private static int idx(int x, int y, int m) { return x * m + y; }

    // pack into long: rPos(4) | bPos(4) | rMask(16) | bMask(16) => 40 bits
    private static long key(int rPos, int bPos, int rMask, int bMask) {
        return ((long)rPos)
                | ((long)bPos << 4)
                | ((long)rMask << 8)
                | ((long)bMask << 24);
    }

    public int solution(int[][] maze) {
        int n = maze.length;
        int m = maze[0].length;

        int rStart = -1, bStart = -1, rGoal = -1, bGoal = -1;
        boolean[] wall = new boolean[16];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int v = maze[i][j];
                int p = idx(i, j, m);
                if (v == 5) wall[p] = true;
                else if (v == 1) rStart = p;
                else if (v == 2) bStart = p;
                else if (v == 3) rGoal = p;
                else if (v == 4) bGoal = p;
            }
        }

        int rMask0 = 1 << rStart;
        int bMask0 = 1 << bStart;

        ArrayDeque<Node> q = new ArrayDeque<>();
        HashSet<Long> visited = new HashSet<>();

        q.add(new Node(rStart, bStart, rMask0, bMask0, 0));
        visited.add(key(rStart, bStart, rMask0, bMask0));

        // 사방
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.rPos == rGoal && cur.bPos == bGoal) {
                return cur.dist;
            }

            boolean rDone = (cur.rPos == rGoal);
            boolean bDone = (cur.bPos == bGoal);

            // generate red moves
            int[] rNextPos = new int[4];
            int[] rNextMask = new int[4];
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
                    if ((cur.rMask & (1 << np)) != 0) continue; // red revisit 금지
                    rNextPos[rCnt] = np;
                    rNextMask[rCnt] = cur.rMask | (1 << np);
                    rCnt++;
                }
            }

            // generate blue moves
            int[] bNextPos = new int[4];
            int[] bNextMask = new int[4];
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
                    if ((cur.bMask & (1 << np)) != 0) continue; // blue revisit 금지
                    bNextPos[bCnt] = np;
                    bNextMask[bCnt] = cur.bMask | (1 << np);
                    bCnt++;
                }
            }

            // 도착칸이 아닌 수레는 "반드시" 움직여야 하는데, 선택지가 없으면 막힘
            if (!rDone && rCnt == 0) continue;
            if (!bDone && bCnt == 0) continue;

            // combine moves with constraints
            for (int i = 0; i < rCnt; i++) {
                for (int j = 0; j < bCnt; j++) {
                    int rn = rNextPos[i], bn = bNextPos[j];

                    // same cell
                    if (rn == bn) continue;

                    boolean rMoved = rn != cur.rPos;
                    boolean bMoved = bn != cur.bPos;

                    // swap (자리 바꾸기) 금지: 둘 다 움직였을 때만 체크
                    if (rMoved && bMoved && rn == cur.bPos && bn == cur.rPos) continue;

                    int rnm = rNextMask[i], bnm = bNextMask[j];
                    long kkey = key(rn, bn, rnm, bnm);
                    if (visited.contains(kkey)) continue;
                    visited.add(kkey);

                    q.add(new Node(rn, bn, rnm, bnm, cur.dist + 1));
                }
            }
        }

        return 0;
    }
}