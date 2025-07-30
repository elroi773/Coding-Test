function solution(s, skip, index) {
    // 1. 유효한 알파벳 리스트 만들기
    const alphabet = 'abcdefghijklmnopqrstuvwxyz';
    const valid = [...alphabet].filter(ch => !skip.includes(ch));

    // 2. 변환
    let answer = '';
    for (let ch of s) {
        let curIndex = valid.indexOf(ch);
        let newIndex = (curIndex + index) % valid.length;
        answer += valid[newIndex];
    }

    return answer;
}
