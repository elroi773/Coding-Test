function solution(rectangle, characterX, characterY, itemX, itemY) {
  const MAX = 102; // 1~50 -> *2 하면 2~100, 여유 포함
  const board = Array.from({ length: MAX }, () => Array(MAX).fill(0));

  // 1) 모든 직사각형 영역을 1로 채우기 (겹치는 부분 포함)
  for (const [x1o, y1o, x2o, y2o] of rectangle) {
    const x1 = x1o * 2, y1 = y1o * 2, x2 = x2o * 2, y2 = y2o * 2;
    for (let x = x1; x <= x2; x++) {
      for (let y = y1; y <= y2; y++) {
        board[x][y] = 1;
      }
    }
  }

  // 2) 각 직사각형 내부를 0으로 지우기 (테두리만 남김)
  for (const [x1o, y1o, x2o, y2o] of rectangle) {
    const x1 = x1o * 2, y1 = y1o * 2, x2 = x2o * 2, y2 = y2o * 2;
    for (let x = x1 + 1; x <= x2 - 1; x++) {
      for (let y = y1 + 1; y <= y2 - 1; y++) {
        board[x][y] = 0;
      }
    }
  }

  // 3) BFS: board==1(테두리)만 이동
  const sx = characterX * 2, sy = characterY * 2;
  const tx = itemX * 2, ty = itemY * 2;

  const visited = Array.from({ length: MAX }, () => Array(MAX).fill(false));
  const q = [];
  let head = 0;

  q.push([sx, sy, 0]);
  visited[sx][sy] = true;

  const dx = [1, -1, 0, 0];
  const dy = [0, 0, 1, -1];

  while (head < q.length) {
    const [x, y, d] = q[head++];

    if (x === tx && y === ty) return d / 2; // 2배 좌표 거리 -> 원래 거리

    for (let dir = 0; dir < 4; dir++) {
      const nx = x + dx[dir];
      const ny = y + dy[dir];

      if (nx < 0 || ny < 0 || nx >= MAX || ny >= MAX) continue;
      if (visited[nx][ny]) continue;
      if (board[nx][ny] !== 1) continue; // 테두리만 이동

      visited[nx][ny] = true;
      q.push([nx, ny, d + 1]);
    }
  }

  return 0; // 문제 조건상 도달 가능
}