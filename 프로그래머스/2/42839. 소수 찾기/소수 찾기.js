function solution(numbers) {
    const nums = new Set();

    // 소수 판별 함수
    function isPrime(n) {
        if (n < 2) return false;
        for (let i = 2; i * i <= n; i++) {
            if (n % i === 0) return false;
        }
        return true;
    }

    // 모든 조합 만들기 (DFS)
    function dfs(current, used) {
        if (current !== "") {
            nums.add(Number(current));
        }

        for (let i = 0; i < numbers.length; i++) {
            if (!used[i]) {
                used[i] = true;
                dfs(current + numbers[i], used);
                used[i] = false;
            }
        }
    }

    dfs("", Array(numbers.length).fill(false));

    // 소수 개수 세기
    let count = 0;
    nums.forEach(n => {
        if (isPrime(n)) count++;
    });

    return count;
}
