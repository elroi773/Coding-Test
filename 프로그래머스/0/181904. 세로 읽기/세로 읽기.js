function solution(my_string, m, c) {
    let answer = '';
    // 전체 줄 수는 my_string 길이 / m
    for (let i = 0; i < my_string.length / m; i++) {
        // i번째 줄에서 c번째 열의 문자는 인덱스 (i * m + (c - 1))
        answer += my_string[i * m + (c - 1)];
    }
    return answer;
}
