function solution(elements) {
  const n = elements.length;
  const arr = elements.concat(elements); // 원형 처리: 배열을 두 번 이어붙이기

  // 누적합(prefix sum) 배열
  const prefix = new Array(2 * n + 1).fill(0);
  for (let i = 0; i < 2 * n; i++) {
    prefix[i + 1] = prefix[i] + arr[i];
  }

  const sums = new Set();

  // 부분 수열 길이: 1 ~ n
  for (let length = 1; length <= n; length++) {
    // 시작 인덱스: 0 ~ n-1 (원형에서 한 바퀴만 보면 됨)
    for (let start = 0; start < n; start++) {
      const sum = prefix[start + length] - prefix[start];
      sums.add(sum);
    }
  }

  return sums.size;
}
