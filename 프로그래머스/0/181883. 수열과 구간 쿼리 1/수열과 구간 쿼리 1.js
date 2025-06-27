function solution(arr, queries) {
    // 각 쿼리 [s, e]에 대해 s ≤ i ≤ e 범위의 값에 +1
    for (const [s, e] of queries) {
        for (let i = s; i <= e; i++) {
            arr[i] += 1;
        }
    }
    return arr;
}
