function solution(n, times) {
  times.sort((a, b) => a - b);

  let left = 1n;
  let right = BigInt(times[times.length - 1]) * BigInt(n); // 최악의 경우
  let answer = right;

  while (left <= right) {
    const mid = (left + right) / 2n;

    let processed = 0n;
    for (const t of times) {
      processed += mid / BigInt(t);
      if (processed >= BigInt(n)) break;
    }

    if (processed >= BigInt(n)) {
      answer = mid;
      right = mid - 1n;
    } else {
      left = mid + 1n;
    }
  }

  // 프로그래머스는 Number로도 통과하지만, BigInt로 안전하게 계산했으니 변환해서 리턴
  return Number(answer);
}