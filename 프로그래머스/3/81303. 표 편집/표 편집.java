import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        int[] prev = new int[n];
        int[] next = new int[n];
        boolean[] removed = new boolean[n];

        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        next[n - 1] = -1;

        // 삭제 스택: {idx, prevIdx, nextIdx}
        ArrayDeque<int[]> stack = new ArrayDeque<>();

        int cur = k;

        for (String s : cmd) {
            char op = s.charAt(0);

            if (op == 'U') {
                int x = Integer.parseInt(s.substring(2));
                for (int i = 0; i < x; i++) cur = prev[cur];

            } else if (op == 'D') {
                int x = Integer.parseInt(s.substring(2));
                for (int i = 0; i < x; i++) cur = next[cur];

            } else if (op == 'C') {
                removed[cur] = true;
                int p = prev[cur];
                int nx = next[cur];
                stack.push(new int[]{cur, p, nx});

                // 연결 끊기
                if (p != -1) next[p] = nx;
                if (nx != -1) prev[nx] = p;

                // 선택 이동
                cur = (nx != -1) ? nx : p;

            } else { // 'Z'
                int[] rec = stack.pop();
                int idx = rec[0];
                int p = rec[1];
                int nx = rec[2];

                removed[idx] = false;

                // 연결 복구
                if (p != -1) next[p] = idx;
                if (nx != -1) prev[nx] = idx;
                prev[idx] = p;
                next[idx] = nx;
            }
        }

        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) sb.append(removed[i] ? 'X' : 'O');
        return sb.toString();
    }
}