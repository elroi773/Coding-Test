function solution(sequence) {
    let maxSum = 0;

    let curr1 = 0; // [1, -1, 1, -1 ...]
    let curr2 = 0; // [-1, 1, -1, 1 ...]

    for (let i = 0; i < sequence.length; i++) {
        const sign1 = (i % 2 === 0) ? 1 : -1;
        const sign2 = -sign1;

        const v1 = sequence[i] * sign1;
        const v2 = sequence[i] * sign2;

        curr1 = Math.max(v1, curr1 + v1);
        curr2 = Math.max(v2, curr2 + v2);

        maxSum = Math.max(maxSum, curr1, curr2);
    }

    return maxSum;
}
