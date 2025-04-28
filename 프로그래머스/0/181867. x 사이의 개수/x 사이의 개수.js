function solution(myString) {
    var parts = myString.split('x'); // "x"를 기준으로 문자열 분리
    var answer = parts.map(part => part.length); // 각 부분 문자열의 길이로 변환
    return answer;
}
