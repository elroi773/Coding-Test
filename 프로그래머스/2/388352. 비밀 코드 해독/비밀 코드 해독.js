function solution(n, q, ans) {
    let answer = 0;
    const m = q.length;      // 시도 횟수
    const code = new Array(5); // 현재 만들고 있는 비밀 코드 후보 5개 숫자

    // code[5]와 row[5] 사이의 공통 원소 개수 (둘 다 오름차순)
    function intersectionCount(code, row) {
        let i = 0, j = 0, cnt = 0;
        while (i < 5 && j < 5) {
            if (code[i] === row[j]) {
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

    // 현재 code[]가 모든 시도(q, ans)를 만족하는지 확인
    function isValid() {
        for (let i = 0; i < m; i++) {
            const matches = intersectionCount(code, q[i]);
            if (matches !== ans[i]) return false;
        }
        return true;
    }

    // DFS로 1~n 중 5개의 조합 생성
    function dfs(idx, start) {
        if (idx === 5) { // 5개 다 뽑았으면 검증
            if (isValid()) answer++;
            return;
        }

        // 남은 자리를 채울 수 있는 최대 숫자까지만 반복
        for (let num = start; num <= n - (5 - idx) + 1; num++) {
            code[idx] = num;
            dfs(idx + 1, num + 1);
        }
    }

    dfs(0, 1); // code[0]부터 채우기 시작, 숫자 1에서 시작

    return answer;
}