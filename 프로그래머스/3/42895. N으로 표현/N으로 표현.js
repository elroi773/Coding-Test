function solution(N, number) {
  if (N === number) return 1;

  const dp = Array.from({ length: 9 }, () => new Set()); // dp[i] = N을 i번 써서 만들 수 있는 값들

  for (let i = 1; i <= 8; i++) {
    // 이어붙인 수: N, NN, NNN...
    const concat = Number(String(N).repeat(i));
    dp[i].add(concat);

    // j + (i-j)로 분할해서 조합
    for (let j = 1; j < i; j++) {
      for (const a of dp[j]) {
        for (const b of dp[i - j]) {
          dp[i].add(a + b);
          dp[i].add(a - b);
          dp[i].add(a * b);
          if (b !== 0) dp[i].add(Math.trunc(a / b)); // 나머지 무시(0쪽으로 절삭)
        }
      }
    }

    if (dp[i].has(number)) return i;
  }

  return -1;
}