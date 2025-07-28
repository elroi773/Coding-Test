function solution(s) {
    let answer = [];
    let lastIndex = {}; // 문자별 마지막 등장 위치 저장

    for (let i = 0; i < s.length; i++) {
        const char = s[i];

        if (lastIndex.hasOwnProperty(char)) {
            answer.push(i - lastIndex[char]);
        } else {
            answer.push(-1);
        }

        lastIndex[char] = i; // 현재 위치 갱신
    }

    return answer;
}
