function solution(data, col, row_begin, row_end) {
  // 1. 정렬
  data.sort((a, b) => {
    if (a[col - 1] === b[col - 1]) return b[0] - a[0];
    return a[col - 1] - b[col - 1];
  });

  // 2. S_i 계산 및 XOR 누적
  let answer = 0;
  for (let i = row_begin; i <= row_end; i++) {
    const row = data[i - 1];
    const S_i = row.reduce((sum, value) => sum + (value % i), 0);
    answer ^= S_i;
  }

  return answer;
}
