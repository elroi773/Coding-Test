function solution(A, B) {
    const a = [...A].sort((x, y) => x - y);
    const b = [...B].sort((x, y) => x - y);

    let i = 0; // A 포인터
    let j = 0; // B 포인터
    let answer = 0;

    // B에서 현재 A를 이길 수 있는 가장 작은 수를 매칭
    while (i < a.length && j < b.length) {
        if (b[j] > a[i]) {
            answer++;
            i++;
            j++;
        } else {
            j++;
        }
    }

    return answer;
}