function solution(n, tops) {
  const MOD = 10007;

  // free: 현재 정점이 아직 매칭되지 않아(비어 있어) 오른쪽과 붙을 수 있는 상태
  // occ : 현재 정점이 이미 왼쪽/위와 붙어서 점유된 상태
  let free = 1;
  let occ = 0;

  // 경로 정점 총 2n+1개: v1..v(2n+1)
  // j=2..2n+1 처리
  for (let j = 2; j <= 2 * n + 1; j++) {
    let hasTop = 0;
    if (j % 2 === 0) {
      const idx = j / 2 - 1; // 0-based
      hasTop = tops[idx];    // 0 or 1
    }

    const newFree = (free + occ) % MOD;        // 단독(정삼각형)
    let newOcc = free % MOD;                   // 왼쪽과 마름모(경로 간선)

    if (hasTop === 1) {
      newOcc = (newOcc + free + occ) % MOD;    // 위 가지와 마름모
    }

    free = newFree;
    occ = newOcc;
  }

  return (free + occ) % MOD;
}