function solution(n, build_frame) {
  const S = new Set(); // "x,y,a" 형태로 저장

  const key = (x, y, a) => `${x},${y},${a}`;
  const has = (x, y, a) => S.has(key(x, y, a));

  // 구조물 1개가 규칙을 만족하는지
  const validOne = (x, y, a) => {
    if (a === 0) {
      // 기둥: 바닥 OR 아래 기둥 OR 보 끝 위
      return (
        y === 0 ||
        has(x, y - 1, 0) ||
        has(x, y, 1) ||
        has(x - 1, y, 1)
      );
    } else {
      // 보: 한쪽 끝이 기둥 위 OR 양쪽 끝이 보로 연결
      return (
        has(x, y - 1, 0) ||
        has(x + 1, y - 1, 0) ||
        (has(x - 1, y, 1) && has(x + 1, y, 1))
      );
    }
  };

  // 전체 구조물이 모두 유효한지
  const validAll = () => {
    for (const item of S) {
      const [x, y, a] = item.split(",").map(Number);
      if (!validOne(x, y, a)) return false;
    }
    return true;
  };

  for (const [x, y, a, b] of build_frame) {
    const k = key(x, y, a);

    if (b === 1) {
      // 설치
      S.add(k);
      if (!validAll()) S.delete(k); // 롤백
    } else {
      // 삭제
      S.delete(k);
      if (!validAll()) S.add(k); // 롤백
    }
  }

  // 결과 만들기
  const answer = [...S]
    .map((s) => s.split(",").map(Number))
    .sort((p, q) => (p[0] - q[0]) || (p[1] - q[1]) || (p[2] - q[2]));

  return answer;
}