function solution(n, t, m, p) {
    let answer = '';
    let seq = '';
    let num = 0;

    // 필요한 길이만큼 문자열 만들기
    while (seq.length < t * m) {
        seq += num.toString(n).toUpperCase();
        num++;
    }

    // 튜브가 말해야 할 숫자만 뽑기
    for (let i = p - 1; answer.length < t; i += m) {
        answer += seq[i];
    }

    return answer;
}
