function solution(n) {
  const MOD = 1_000_000_007;

  // dp[0]..dp[6]
  const base = [1, 1, 3, 10, 23, 62, 170];
  if (n <= 6) return base[n];

  const dp = Array(n + 1).fill(0);
  const s = Array(n + 1).fill(0); // s[i] = dp[i] + s[i-3]

  for (let i = 0; i <= 6; i++) dp[i] = base[i];

  s[0] = dp[0];
  s[1] = dp[1];
  s[2] = dp[2];
  for (let i = 3; i <= 6; i++) {
    s[i] = (dp[i] + s[i - 3]) % MOD;
  }

  for (let i = 7; i <= n; i++) {
    let val = 0;
    val += dp[i - 1];
    val += 2 * dp[i - 2];
    val += 5 * dp[i - 3];
    val += 2 * s[i - 4];
    val += 2 * s[i - 5];
    val += 4 * s[i - 6];

    dp[i] = val % MOD;
    s[i] = (dp[i] + s[i - 3]) % MOD;
  }

  return dp[n];
}