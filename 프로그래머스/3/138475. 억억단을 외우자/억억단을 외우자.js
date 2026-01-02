function solution(e, starts) {
  // 1) 약수 개수
  const cnt = new Array(e + 1).fill(0);
  for (let i = 1; i <= e; i++) {
    for (let j = i; j <= e; j += i) cnt[j]++;
  }

  // 2) best[i] = [i..e] 구간 정답
  const best = new Array(e + 1);
  let bestNum = e;
  let bestCnt = cnt[e];
  best[e] = e;

  for (let i = e - 1; i >= 1; i--) {
    if (cnt[i] >= bestCnt) { // 동점이면 더 작은 i가 이김
      bestCnt = cnt[i];
      bestNum = i;
    }
    best[i] = bestNum;
  }

  // 3) 질의
  return starts.map(s => best[s]);
}