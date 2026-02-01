function solution(a) {
  const n = a.length;
  if (n <= 1) return n;

  const INF = Number.POSITIVE_INFINITY;

  // rightMin[i] = min(a[i+1..n-1]) (없으면 INF)
  const rightMin = new Array(n);
  let cur = INF;
  for (let i = n - 1; i >= 0; i--) {
    rightMin[i] = cur;
    if (a[i] < cur) cur = a[i];
  }

  let answer = 0;
  let leftMin = INF;
  for (let i = 0; i < n; i++) {
    if (a[i] <= leftMin || a[i] <= rightMin[i]) answer++;
    if (a[i] < leftMin) leftMin = a[i];
  }

  return answer;
}