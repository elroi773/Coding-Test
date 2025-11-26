function solution(want, number, discount) {
    let answer = 0;

    // 1. want 배열과 number 배열을 Map으로 저장
    const wantMap = new Map();
    for (let i = 0; i < want.length; i++) {
        wantMap.set(want[i], number[i]);
    }

    // 2. discount 배열을 10일씩 확인
    for (let i = 0; i <= discount.length - 10; i++) {
        const windowMap = new Map();

        // 10일 동안 각 상품 카운트
        for (let j = i; j < i + 10; j++) {
            const item = discount[j];
            windowMap.set(item, (windowMap.get(item) || 0) + 1);
        }

        // 3. Map 비교
        let match = true;
        for (let [item, qty] of wantMap) {
            if (windowMap.get(item) !== qty) {
                match = false;
                break;
            }
        }

        if (match) answer++;
    }

    return answer;
}
