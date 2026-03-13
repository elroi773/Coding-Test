function solution(s) {
    let answer = 1;

    function expand(left, right) {
        while (left >= 0 && right < s.length && s[left] === s[right]) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    for (let i = 0; i < s.length; i++) {
        const odd = expand(i, i);       // 홀수 길이
        const even = expand(i, i + 1);  // 짝수 길이

        answer = Math.max(answer, odd, even);
    }

    return answer;
}