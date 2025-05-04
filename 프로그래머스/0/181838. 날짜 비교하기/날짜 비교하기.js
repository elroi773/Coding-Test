function solution(date1, date2) {
    for (let i = 0; i < 3; i++) {
        if (date1[i] < date2[i]) return 1;
        if (date1[i] > date2[i]) return 0;
    }
    return 0; // 모든 값이 같을 경우
}
