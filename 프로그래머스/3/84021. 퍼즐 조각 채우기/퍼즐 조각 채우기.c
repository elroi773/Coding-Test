#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

typedef struct {
    int k;          // 칸 수 (1~6)
    int p[6][2];    // 정규화된 좌표 (r,c), (0,0) 기준, 정렬됨
} Shape;

static void sort_pts(int pts[][2], int k) {
    // k<=6 이라 단순 정렬
    for (int i = 0; i < k; i++) {
        for (int j = i + 1; j < k; j++) {
            if (pts[j][0] < pts[i][0] || (pts[j][0] == pts[i][0] && pts[j][1] < pts[i][1])) {
                int tr = pts[i][0], tc = pts[i][1];
                pts[i][0] = pts[j][0]; pts[i][1] = pts[j][1];
                pts[j][0] = tr;        pts[j][1] = tc;
            }
        }
    }
}

static void normalize(int in[][2], int k, int out[][2]) {
    int minr = in[0][0], minc = in[0][1];
    for (int i = 1; i < k; i++) {
        if (in[i][0] < minr) minr = in[i][0];
        if (in[i][1] < minc) minc = in[i][1];
    }
    for (int i = 0; i < k; i++) {
        out[i][0] = in[i][0] - minr;
        out[i][1] = in[i][1] - minc;
    }
    sort_pts(out, k);
}

static void rotate90(int in[][2], int k, int out[][2]) {
    // (r,c) -> (c, -r)
    for (int i = 0; i < k; i++) {
        out[i][0] = in[i][1];
        out[i][1] = -in[i][0];
    }
}

static int lex_cmp(int a[][2], int b[][2], int k) {
    // a < b => -1, 같으면 0, a > b => 1
    for (int i = 0; i < k; i++) {
        if (a[i][0] != b[i][0]) return (a[i][0] < b[i][0]) ? -1 : 1;
        if (a[i][1] != b[i][1]) return (a[i][1] < b[i][1]) ? -1 : 1;
    }
    return 0;
}

static void canonicalize(int raw[][2], int k, Shape* outShape) {
    int pts[6][2];
    for (int i = 0; i < k; i++) { pts[i][0] = raw[i][0]; pts[i][1] = raw[i][1]; }

    int best[6][2];
    bool hasBest = false;

    for (int rot = 0; rot < 4; rot++) {
        int norm[6][2];
        normalize(pts, k, norm);

        if (!hasBest || lex_cmp(norm, best, k) < 0) {
            for (int i = 0; i < k; i++) { best[i][0] = norm[i][0]; best[i][1] = norm[i][1]; }
            hasBest = true;
        }

        // ✅ 다음 회전은 "정규화된 좌표" 기준으로 돌려야 회전 기준이 흔들리지 않음
        int r90[6][2];
        rotate90(norm, k, r90);
        for (int i = 0; i < k; i++) { pts[i][0] = r90[i][0]; pts[i][1] = r90[i][1]; }
    }

    outShape->k = k;
    for (int i = 0; i < k; i++) { outShape->p[i][0] = best[i][0]; outShape->p[i][1] = best[i][1]; }
}

static bool shape_equal(const Shape* a, const Shape* b) {
    if (a->k != b->k) return false;
    for (int i = 0; i < a->k; i++) {
        if (a->p[i][0] != b->p[i][0] || a->p[i][1] != b->p[i][1]) return false;
    }
    return true;
}

static int extract_shapes(int** grid, int rows, int cols, int target, Shape* outShapes) {
    bool* visited = (bool*)calloc((size_t)rows * (size_t)cols, sizeof(bool));
    if (!visited) return 0;

    int dr[4] = {1, -1, 0, 0};
    int dc[4] = {0, 0, 1, -1};

    int count = 0;

    int* qr = (int*)malloc((size_t)rows * (size_t)cols * sizeof(int));
    int* qc = (int*)malloc((size_t)rows * (size_t)cols * sizeof(int));

    for (int r = 0; r < rows; r++) {
        for (int c = 0; c < cols; c++) {
            int idx = r * cols + c;
            if (visited[idx]) continue;
            if (grid[r][c] != target) continue;

            visited[idx] = true;

            int head = 0, tail = 0;
            qr[tail] = r; qc[tail] = c; tail++;

            int raw[6][2];
            int k = 0;

            while (head < tail) {
                int cr = qr[head], cc = qc[head];
                head++;

                // 문제 조건상 컴포넌트 크기 <= 6
                raw[k][0] = cr;
                raw[k][1] = cc;
                k++;

                for (int d = 0; d < 4; d++) {
                    int nr = cr + dr[d], nc = cc + dc[d];
                    if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) continue;
                    int nidx = nr * cols + nc;
                    if (visited[nidx]) continue;
                    if (grid[nr][nc] != target) continue;
                    visited[nidx] = true;
                    qr[tail] = nr; qc[tail] = nc; tail++;
                }
            }

            canonicalize(raw, k, &outShapes[count]);
            count++;
        }
    }

    free(qr);
    free(qc);
    free(visited);
    return count;
}

// game_board_rows는 2차원 배열 game_board의 행 길이, game_board_cols는 2차원 배열 game_board의 열 길이입니다.
// table_rows는 2차원 배열 table의 행 길이, table_cols는 2차원 배열 table의 열 길이입니다.
int solution(int** game_board, size_t game_board_rows, size_t game_board_cols,
             int** table, size_t table_rows, size_t table_cols) {

    int n = (int)game_board_rows;
    int m = (int)game_board_cols;

    // 최대 컴포넌트 수는 n*m
    int maxComp = n * m;
    Shape* holes = (Shape*)malloc((size_t)maxComp * sizeof(Shape));
    Shape* blocks = (Shape*)malloc((size_t)maxComp * sizeof(Shape));

    int holeCnt = extract_shapes(game_board, n, m, 0, holes);
    int blockCnt = extract_shapes(table, (int)table_rows, (int)table_cols, 1, blocks);

    bool* used = (bool*)calloc((size_t)blockCnt, sizeof(bool));

    int filled = 0;
    for (int i = 0; i < holeCnt; i++) {
        for (int j = 0; j < blockCnt; j++) {
            if (used[j]) continue;
            if (holes[i].k != blocks[j].k) continue;
            if (shape_equal(&holes[i], &blocks[j])) {
                used[j] = true;
                filled += holes[i].k;
                break;
            }
        }
    }

    free(used);
    free(holes);
    free(blocks);
    return filled;
}