function solution(n, t, m, timetable) {
  // "HH:MM" -> minutes
  const toMin = (time) => {
    const [h, mm] = time.split(":").map(Number);
    return h * 60 + mm;
  };

  // minutes -> "HH:MM"
  const toStr = (minutes) => {
    const h = String(Math.floor(minutes / 60)).padStart(2, "0");
    const mm = String(minutes % 60).padStart(2, "0");
    return `${h}:${mm}`;
  };

  // 크루 도착 시간 정렬
  const crew = timetable.map(toMin).sort((a, b) => a - b);

  let idx = 0; // 아직 탑승하지 않은 크루 인덱스

  for (let i = 0; i < n; i++) {
    const busTime = 9 * 60 + i * t; // 09:00 + i*t
    let boarded = 0;

    // 현재 셔틀에 탈 수 있는 크루 태우기
    while (idx < crew.length && crew[idx] <= busTime && boarded < m) {
      idx++;
      boarded++;
    }

    // 마지막 셔틀 처리
    if (i === n - 1) {
      if (boarded < m) {
        // 자리 남음
        return toStr(busTime);
      } else {
        // 자리 꽉 참: 마지막 탑승자보다 1분 먼저
        return toStr(crew[idx - 1] - 1);
      }
    }
  }

  return "";
}