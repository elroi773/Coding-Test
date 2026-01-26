import java.util.*;

class Solution {
    static final int N = 4;
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    static int idx(int r, int c) { return r * 4 + c; }

    static boolean inRange(int r, int c) {
        return 0 <= r && r < 4 && 0 <= c && c < 4;
    }

    // Ctrl 이동: 해당 방향 가장 가까운 카드, 없으면 끝칸
    static int ctrlMove(int from, int dir, int mask) {
        int r = from / 4, c = from % 4;
        while (true) {
            int nr = r + dr[dir], nc = c + dc[dir];
            if (!inRange(nr, nc)) break;
            r = nr; c = nc;
            int ni = idx(r, c);
            if ((mask & (1 << ni)) != 0) return ni; // 카드 발견
        }
        return idx(r, c); // 마지막 칸
    }

    static int stepMove(int from, int dir) {
        int r = from / 4, c = from % 4;
        int nr = r + dr[dir], nc = c + dc[dir];
        if (!inRange(nr, nc)) return from;
        return idx(nr, nc);
    }

    // BFS로 start -> target 최소 이동 횟수
    static int dist(int start, int target, int mask) {
        if (start == target) return 0;
        int[] vis = new int[16];
        Arrays.fill(vis, -1);
        int[] q = new int[16];
        int head = 0, tail = 0;

        q[tail++] = start;
        vis[start] = 0;

        while (head < tail) {
            int u = q[head++];
            int d = vis[u];

            for (int dir = 0; dir < 4; dir++) {
                int v1 = stepMove(u, dir);
                if (vis[v1] == -1) {
                    vis[v1] = d + 1;
                    if (v1 == target) return vis[v1];
                    q[tail++] = v1;
                }
                int v2 = ctrlMove(u, dir, mask);
                if (vis[v2] == -1) {
                    vis[v2] = d + 1;
                    if (v2 == target) return vis[v2];
                    q[tail++] = v2;
                }
            }
        }
        return vis[target];
    }

    // 메모 키: (mask << 4) | cursor
    static long key(int mask, int cursor) {
        return (((long) mask) << 4) | (long) cursor;
    }

    int[] typeList;
    int[][] pos; // pos[t][0/1] = 위치
    Map<Long, Integer> memo;

    int dfs(int mask, int cursor) {
        if (mask == 0) return 0;
        long k = key(mask, cursor);
        Integer cached = memo.get(k);
        if (cached != null) return cached;

        int best = Integer.MAX_VALUE;

        for (int t : typeList) {
            int a = pos[t][0];
            int b = pos[t][1];
            boolean ha = (mask & (1 << a)) != 0;
            boolean hb = (mask & (1 << b)) != 0;
            if (!ha && !hb) continue; // 이미 제거됨

            int nextMask = mask & ~(1 << a) & ~(1 << b);

            // 순서 1: cursor->a(Enter)->b(Enter)
            int cost1 = dist(cursor, a, mask) + 1 + dist(a, b, mask) + 1;
            best = Math.min(best, cost1 + dfs(nextMask, b));

            // 순서 2: cursor->b(Enter)->a(Enter)
            int cost2 = dist(cursor, b, mask) + 1 + dist(b, a, mask) + 1;
            best = Math.min(best, cost2 + dfs(nextMask, a));
        }

        memo.put(k, best);
        return best;
    }

    public int solution(int[][] board, int r, int c) {
        pos = new int[7][2];
        int[] cnt = new int[7];

        int mask = 0;
        boolean[] exist = new boolean[7];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int v = board[i][j];
                if (v != 0) {
                    int p = idx(i, j);
                    mask |= (1 << p);
                    exist[v] = true;
                    pos[v][cnt[v]++] = p;
                }
            }
        }

        int k = 0;
        for (int v = 1; v <= 6; v++) if (exist[v]) k++;
        typeList = new int[k];
        int tIdx = 0;
        for (int v = 1; v <= 6; v++) if (exist[v]) typeList[tIdx++] = v;

        memo = new HashMap<>();
        return dfs(mask, idx(r, c));
    }
}