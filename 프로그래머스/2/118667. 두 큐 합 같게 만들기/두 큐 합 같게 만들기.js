function solution(queue1, queue2) {
    let sum1 = queue1.reduce((a, b) => a + b, 0);
    let sum2 = queue2.reduce((a, b) => a + b, 0);
    const total = sum1 + sum2;
    
    if (total % 2 !== 0) return -1; // 불가능
    const target = total / 2;
    
    const n = queue1.length;
    const combined = queue1.concat(queue2, queue1, queue2); // 2배 반복
    
    let start = 0;
    let end = n;
    let currSum = sum1;
    let cnt = 0;
    const maxCnt = n * 3; // 안전 마진

    while (cnt <= maxCnt) {
        if (currSum === target) return cnt;

        if (currSum > target) {
            currSum -= combined[start++];
        } else {
            currSum += combined[end++];
        }
        cnt++;
    }

    return -1;
}