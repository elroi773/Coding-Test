def solution(n, t, m, timetable):
    # "HH:MM" -> minutes
    def to_min(time_str):
        h, mm = map(int, time_str.split(":"))
        return h * 60 + mm

    # minutes -> "HH:MM"
    def to_str(minutes):
        h = minutes // 60
        mm = minutes % 60
        return f"{h:02d}:{mm:02d}"

    # 크루 도착 시간을 분 단위로 변환 후 정렬
    crew = sorted(to_min(x) for x in timetable)

    # 셔틀 도착 시간들 (09:00부터 n회, t분 간격)
    shuttle_times = [9 * 60 + i * t for i in range(n)]

    idx = 0  # 아직 탑승하지 않은 크루의 시작 인덱스

    for i in range(n):
        bus_time = shuttle_times[i]
        boarded = 0

        # 현재 셔틀에 탈 수 있는 만큼 태우기
        while idx < len(crew) and crew[idx] <= bus_time and boarded < m:
            idx += 1
            boarded += 1

        # 마지막 셔틀 처리
        if i == n - 1:
            # 자리가 남으면 콘은 셔틀 도착 시각에 와도 탑승 가능
            if boarded < m:
                return to_str(bus_time)
            # 자리가 꽉 찼으면, 마지막으로 탄 사람보다 1분 일찍 와야 함
            else:
                # 마지막으로 탄 사람 = crew[idx - 1]
                return to_str(crew[idx - 1] - 1)