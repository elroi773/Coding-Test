function solution(n) {
    let answer = 0;
    // n을 문자열로 바꿔서 각 자리 순회
    let str = String(n);

    for (let i = 0; i < str.length; i++) {
        answer += Number(str[i]); // 각 자리 숫자를 더함
    }

    return answer;
}

// 실행 테스트
console.log(solution(123)); // 6
console.log(solution(987)); // 24
