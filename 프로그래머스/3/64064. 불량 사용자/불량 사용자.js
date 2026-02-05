function solution(user_id, banned_id) {
  // 패턴 매칭: 길이 같고, '*'는 와일드카드
  const match = (user, ban) => {
    if (user.length !== ban.length) return false;
    for (let i = 0; i < ban.length; i++) {
      if (ban[i] === '*') continue;
      if (user[i] !== ban[i]) return false;
    }
    return true;
  };

  // banned_id 각 패턴별 후보(user index) 목록 만들기
  const candidates = banned_id.map((ban) => {
    const list = [];
    for (let i = 0; i < user_id.length; i++) {
      if (match(user_id[i], ban)) list.push(i);
    }
    return list;
  });

  // 결과를 "순서 무관" 집합으로 저장: 비트마스크(유저 최대 8명)
  const results = new Set();

  const dfs = (idx, usedMask) => {
    if (idx === banned_id.length) {
      results.add(usedMask);
      return;
    }
    for (const ui of candidates[idx]) {
      const bit = 1 << ui;
      if (usedMask & bit) continue; // 이미 사용한 유저
      dfs(idx + 1, usedMask | bit);
    }
  };

  dfs(0, 0);
  return results.size;
}