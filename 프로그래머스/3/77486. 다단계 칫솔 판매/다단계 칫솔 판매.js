function solution(enroll, referral, seller, amount) {
  const n = enroll.length;

  // 이름 -> 인덱스
  const idx = new Map();
  for (let i = 0; i < n; i++) idx.set(enroll[i], i);

  // 부모(추천인) 인덱스: 없으면 -1(센터)
  const parent = new Array(n);
  for (let i = 0; i < n; i++) {
    parent[i] = referral[i] === "-" ? -1 : idx.get(referral[i]);
  }

  const profit = new Array(n).fill(0);

  // 판매 기록 처리
  for (let i = 0; i < seller.length; i++) {
    let cur = idx.get(seller[i]);
    let money = amount[i] * 100;

    while (cur !== -1 && money > 0) {
      const give = Math.floor(money / 10); // 10%
      const keep = money - give;
      profit[cur] += keep;

      cur = parent[cur];
      money = give;
    }
  }

  return profit;
}