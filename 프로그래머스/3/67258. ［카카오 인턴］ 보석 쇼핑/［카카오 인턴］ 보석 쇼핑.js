function solution(gems) {
  const need = new Set(gems).size;   // 포함해야 하는 보석 종류 수
  const count = new Map();

  let l = 0;
  let bestL = 0, bestR = gems.length - 1;
  let bestLen = bestR - bestL;

  for (let r = 0; r < gems.length; r++) {
    const g = gems[r];
    count.set(g, (count.get(g) || 0) + 1);

    // 모든 종류를 포함하면 왼쪽을 가능한 한 줄이기
    while (count.size === need && l <= r) {
      const len = r - l;
      if (len < bestLen || (len === bestLen && l < bestL)) {
        bestLen = len;
        bestL = l;
        bestR = r;
      }

      const leftGem = gems[l];
      const c = count.get(leftGem) - 1;
      if (c === 0) count.delete(leftGem);
      else count.set(leftGem, c);

      l++;
    }
  }

  // 진열대 번호는 1부터 시작
  return [bestL + 1, bestR + 1];
}