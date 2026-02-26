function solution(n, stations, w) {
  let answer = 0;
  const cover = 2 * w + 1;

  let position = 1; // 아직 커버 안 된 시작 아파트(1-based)

  for (const s of stations) {
    const left = s - w;
    const right = s + w;

    // position ~ (left-1) 구간이 비어있으면 채우기
    if (position < left) {
      const gap = left - position;
      answer += Math.ceil(gap / cover); // 올림
      // 또는: answer += Math.floor((gap + cover - 1) / cover);
    }

    // 이 기지국 커버 끝 다음으로 이동
    position = Math.max(position, right + 1);

    if (position > n) break;
  }

  // 마지막 기지국 이후 남은 구간 처리
  if (position <= n) {
    const gap = n - position + 1;
    answer += Math.ceil(gap / cover);
  }

  return answer;
}