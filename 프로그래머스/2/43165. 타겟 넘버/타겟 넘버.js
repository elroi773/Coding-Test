function solution(numbers, target) {
    let answer = 0;

    function dfs(index, sum) {
        // 모든 숫자를 사용한 경우
        if (index === numbers.length) {
            if (sum === target) answer++;
            return;
        }

        // 현재 숫자를 더하는 경우
        dfs(index + 1, sum + numbers[index]);

        // 현재 숫자를 빼는 경우
        dfs(index + 1, sum - numbers[index]);
    }

    dfs(0, 0);
    return answer;
}
