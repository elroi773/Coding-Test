class Solution {
    private int n;
    private int[][] q;
    private int[] ans;
    private int m;          // 시도 횟수 (q.length)
    private int count;      // 가능한 비밀 코드 개수
    private int[] code = new int[5];  // 현재 만들고 있는 비밀 코드 후보

    public int solution(int n, int[][] q, int[] ans) {
        this.n = n;
        this.q = q;
        this.ans = ans;
        this.m = q.length;
        this.count = 0;

        dfs(0, 1);  // code[0] 자리부터, 숫자 1부터 시작해서 5개 뽑기
        return count;
    }

    // 깊이 우선 탐색으로 1~n 중 5개의 조합 생성
    private void dfs(int idx, int start) {
        if (idx == 5) { // 5개 다 뽑았으면 검증
            if (isValid()) {
                count++;
            }
            return;
        }

        // 남은 자리를 채울 수 있는 최대 숫자까지 반복
        // (n - (필요한 남은 개수) + 1) 까지만
        for (int num = start; num <= n - (5 - idx) + 1; num++) {
            code[idx] = num;
            dfs(idx + 1, num + 1);
        }
    }

    // 현재 code[]가 모든 시도(q, ans)를 만족하는지 확인
    private boolean isValid() {
        for (int i = 0; i < m; i++) {
            int matches = intersectionCount(code, q[i]);
            if (matches != ans[i]) {
                return false;   // 하나라도 다르면 이 후보는 탈락
            }
        }
        return true;
    }

    // code[5]와 row[5] (q의 한 행) 사이의 공통 원소 개수 (둘 다 오름차순)
    private int intersectionCount(int[] code, int[] row) {
        int i = 0, j = 0, cnt = 0;
        while (i < 5 && j < 5) {
            if (code[i] == row[j]) {
                cnt++;
                i++;
                j++;
            } else if (code[i] < row[j]) {
                i++;
            } else {
                j++;
            }
        }
        return cnt;
    }
}