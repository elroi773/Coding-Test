function solution(m, n, startX, startY, balls) {
  const answer = [];

  for (const [a, b] of balls) {
    let minDist = Infinity;

    // 1️⃣ 위쪽 벽 반사 (y → 2n - b)
    if (!(startX === a && startY < b)) {
      const y = 2 * n - b;
      const dist = (startX - a) ** 2 + (startY - y) ** 2;
      minDist = Math.min(minDist, dist);
    }

    // 2️⃣ 아래쪽 벽 반사 (y → -b)
    if (!(startX === a && startY > b)) {
      const y = -b;
      const dist = (startX - a) ** 2 + (startY - y) ** 2;
      minDist = Math.min(minDist, dist);
    }

    // 3️⃣ 왼쪽 벽 반사 (x → -a)
    if (!(startY === b && startX > a)) {
      const x = -a;
      const dist = (startX - x) ** 2 + (startY - b) ** 2;
      minDist = Math.min(minDist, dist);
    }

    // 4️⃣ 오른쪽 벽 반사 (x → 2m - a)
    if (!(startY === b && startX < a)) {
      const x = 2 * m - a;
      const dist = (startX - x) ** 2 + (startY - b) ** 2;
      minDist = Math.min(minDist, dist);
    }

    answer.push(minDist);
  }

  return answer;
}
