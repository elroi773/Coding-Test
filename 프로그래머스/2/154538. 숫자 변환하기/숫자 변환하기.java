import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        if (x == y) return 0; // 이미 같은 경우

        boolean[] visited = new boolean[y + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, 0});
        visited[x] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int val = cur[0];
            int cnt = cur[1];

            int[] nexts = {val + n, val * 2, val * 3};

            for (int next : nexts) {
                if (next > y || visited[next]) continue;
                if (next == y) return cnt + 1;

                visited[next] = true;
                queue.add(new int[]{next, cnt + 1});
            }
        }

        return -1; // y로 도달 불가능
    }
}
