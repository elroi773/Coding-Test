function solution(a, b, g, s, w, t) {
  const needG = BigInt(a);
  const needS = BigInt(b);
  const needT = needG + needS;

  const can = (time) => {
    let sumG = 0n, sumS = 0n, sumAll = 0n;

    for (let i = 0; i < g.length; i++) {
      const gi = BigInt(g[i]);
      const si = BigInt(s[i]);
      const wi = BigInt(w[i]);
      const ti = BigInt(t[i]);

      const round = ti * 2n;
      let trips = time / round;
      if (time % round >= ti) trips += 1n; // 마지막 편도 1회 가능

      const cap = trips * wi;

      sumG += (gi < cap ? gi : cap);
      sumS += (si < cap ? si : cap);
      const total = gi + si;
      sumAll += (total < cap ? total : cap);

      if (sumG > needG) sumG = needG;
      if (sumS > needS) sumS = needS;
      if (sumAll > needT) sumAll = needT;
    }

    return sumG >= needG && sumS >= needS && sumAll >= needT;
  };

  let lo = 0n;
  let hi = 4_000_000_000_000_000n; // 4e15

  while (lo < hi) {
    const mid = (lo + hi) / 2n;
    if (can(mid)) hi = mid;
    else lo = mid + 1n;
  }

  // 프로그래머스 JS는 Number로도 통과하는 경우가 많지만 안전하게 BigInt -> Number 변환
  // (문제 반환형이 number로 요구됨)
  return Number(lo);
}