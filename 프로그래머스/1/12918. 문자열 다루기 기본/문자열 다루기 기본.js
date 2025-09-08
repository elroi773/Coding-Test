function solution(s) {
    // 길이가 4 또는 6이 아닌 경우 false
    if (s.length !== 4 && s.length !== 6) {
        return false;
    }

    // 숫자인지 정규식으로 검사
    return /^\d+$/.test(s);
}