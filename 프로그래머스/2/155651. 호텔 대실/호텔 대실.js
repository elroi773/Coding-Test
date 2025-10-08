function solution(book_time) {
  // "HH:MM" → 분으로 변환
  const toMinutes = (time) => {
    const [h, m] = time.split(":").map(Number);
    return h * 60 + m;
  };

  // 1. 예약 시간들을 [시작, 종료+10분] 형태로 변환
  const times = book_time.map(([start, end]) => [toMinutes(start), toMinutes(end) + 10]);

  // 2. 시작 시간 기준으로 정렬
  times.sort((a, b) => a[0] - b[0]);

  // 3. 각 방의 청소 완료 시각을 저장하는 배열
  const rooms = [];

  for (const [start, end] of times) {
    let reused = false;

    // 가장 빨리 비는 방 찾기 (정렬 유지)
    rooms.sort((a, b) => a - b);

    for (let i = 0; i < rooms.length; i++) {
      if (rooms[i] <= start) {
        rooms[i] = end; // 방 재사용
        reused = true;
        break;
      }
    }

    // 재사용할 방이 없다면 새 방 추가
    if (!reused) rooms.push(end);
  }

  // 4. 필요한 최소 객실 수 = 남은 방의 개수
  return rooms.length;
}
