function solution(topping) {
    const n = topping.length;
    let answer = 0;

    const left = new Array(n).fill(0);
    const right = new Array(n).fill(0);

    const leftSet = new Set();
    const rightSet = new Set();

    // 1️⃣ 왼쪽 누적 (0~i)
    for (let i = 0; i < n; i++) {
        leftSet.add(topping[i]);
        left[i] = leftSet.size;
    }

    // 2️⃣ 오른쪽 누적 (i~끝)
    for (let i = n - 1; i >= 0; i--) {
        rightSet.add(topping[i]);
        right[i] = rightSet.size;
    }

    // 3️⃣ 자를 위치 비교
    for (let i = 0; i < n - 1; i++) {
        if (left[i] === right[i + 1]) answer++;
    }

    return answer;
}
