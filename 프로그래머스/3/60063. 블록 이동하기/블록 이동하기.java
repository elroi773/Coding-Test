import java.util.*;

class Solution {
    static class State {
        int r1, c1, r2, c2, t;
        State(int r1, int c1, int r2, int c2, int t) {
            // normalize (lexicographic) so (r1,c1) <= (r2,c2)
            if (r1 < r2 || (r1 == r2 && c1 <= c2)) {
                this.r1 = r1; this.c1 = c1; this.r2 = r2; this.c2 = c2;
            } else {
                this.r1 = r2; this.c1 = c2; this.r2 = r1; this.c2 = c1;
            }
            this.t = t;
        }
    }

    // encode state into long for visited set
    private long encode(int r1, int c1, int r2, int c2) {
        // each coord <= 101 (with padding). pack into 7 bits each safely.
        return (((long) r1) << 21) | (((long) c1) << 14) | (((long) r2) << 7) | (long) c2;
    }

    public int solution(int[][] board) {
        int n = board.length;

        // pad with walls (1) around
        int[][] b = new int[n + 2][n + 2];
        for (int i = 0; i < n + 2; i++) Arrays.fill(b[i], 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) b[i + 1][j + 1] = board[i][j];
        }

        ArrayDeque<State> q = new ArrayDeque<>();
        HashSet<Long> vis = new HashSet<>();

        State start = new State(1, 1, 1, 2, 0);
        q.add(start);
        vis.add(encode(start.r1, start.c1, start.r2, start.c2));

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            State cur = q.poll();

            // goal: either cell hits (n,n) in 1-based coords
            if ((cur.r1 == n && cur.c1 == n) || (cur.r2 == n && cur.c2 == n)) {
                return cur.t;
            }

            int r1 = cur.r1, c1 = cur.c1, r2 = cur.r2, c2 = cur.c2;

            // 1) moves
            for (int k = 0; k < 4; k++) {
                int nr1 = r1 + dr[k], nc1 = c1 + dc[k];
                int nr2 = r2 + dr[k], nc2 = c2 + dc[k];
                if (b[nr1][nc1] == 0 && b[nr2][nc2] == 0) {
                    State nxt = new State(nr1, nc1, nr2, nc2, cur.t + 1);
                    long code = encode(nxt.r1, nxt.c1, nxt.r2, nxt.c2);
                    if (vis.add(code)) q.add(nxt);
                }
            }

            // 2) rotations
            // horizontal (same row)
            if (r1 == r2) {
                for (int dir : new int[]{-1, 1}) { // up / down
                    if (b[r1 + dir][c1] == 0 && b[r2 + dir][c2] == 0) {
                        // pivot at (r1,c1)
                        State nxt1 = new State(r1, c1, r1 + dir, c1, cur.t + 1);
                        long code1 = encode(nxt1.r1, nxt1.c1, nxt1.r2, nxt1.c2);
                        if (vis.add(code1)) q.add(nxt1);

                        // pivot at (r2,c2)
                        State nxt2 = new State(r2, c2, r2 + dir, c2, cur.t + 1);
                        long code2 = encode(nxt2.r1, nxt2.c1, nxt2.r2, nxt2.c2);
                        if (vis.add(code2)) q.add(nxt2);
                    }
                }
            }

            // vertical (same column)
            if (c1 == c2) {
                for (int dir : new int[]{-1, 1}) { // left / right
                    if (b[r1][c1 + dir] == 0 && b[r2][c2 + dir] == 0) {
                        // pivot at (r1,c1)
                        State nxt1 = new State(r1, c1, r1, c1 + dir, cur.t + 1);
                        long code1 = encode(nxt1.r1, nxt1.c1, nxt1.r2, nxt1.c2);
                        if (vis.add(code1)) q.add(nxt1);

                        // pivot at (r2,c2)
                        State nxt2 = new State(r2, c2, r2, c2 + dir, cur.t + 1);
                        long code2 = encode(nxt2.r1, nxt2.c1, nxt2.r2, nxt2.c2);
                        if (vis.add(code2)) q.add(nxt2);
                    }
                }
            }
        }

        return -1; // unreachable per constraints
    }
}