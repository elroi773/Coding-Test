function solution(stones, k) {
  // m명이 건널 수 있는지 체크
  // stones[i] < m 이면 m번째 사람 기준으로 그 돌은 0 이하가 되어 밟을 수 없음
  // 밟을 수 없는 돌이 연속 k개 이상이면 실패
  const canCross = (m) => {
    let consecutive = 0;
    for (const s of stones) {
      if (s < m) {
        consecutive++;
        if (consecutive >= k) return false;
      } else {
        consecutive = 0;
      }
    }
    return true;
  };

  let left = 1;
  let right = 0;
  for (const s of stones) right = Math.max(right, s);

  let answer = 0;
  while (left <= right) {
    const mid = Math.floor((left + right) / 2);
    if (canCross(mid)) {
      answer = mid;     // mid명 가능 -> 더 큰 값 탐색
      left = mid + 1;
    } else {
      right = mid - 1;  // mid명 불가능 -> 더 작은 값 탐색
    }
  }
  return answer;
}