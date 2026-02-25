def solution(lines):
    intervals = []  # (start_ms, end_ms)

    for line in lines:
        _, time_str, dur_str = line.split()

        end_ms = parse_time_to_ms(time_str)
        dur_ms = parse_duration_to_ms(dur_str)
        start_ms = end_ms - dur_ms + 1  # 시작/끝 포함

        intervals.append((start_ms, end_ms))

    answer = 0

    # 각 로그의 시작/끝 시점을 기준으로 1초 구간 [t, t+999] 검사
    for start, end in intervals:
        for window_start in (start, end):
            window_end = window_start + 999
            count = 0

            for s, e in intervals:
                # 구간이 겹치면 카운트
                if e >= window_start and s <= window_end:
                    count += 1

            answer = max(answer, count)

    return answer


def parse_time_to_ms(time_str):
    # "hh:mm:ss.sss"
    h = int(time_str[0:2])
    m = int(time_str[3:5])
    s = int(time_str[6:8])
    ms = int(time_str[9:12])

    return ((h * 3600 + m * 60 + s) * 1000) + ms


def parse_duration_to_ms(dur_str):
    # "2s", "2.0s", "0.351s"
    t = dur_str[:-1]  # remove 's'

    if '.' not in t:
        return int(t) * 1000

    sec_str, frac_str = t.split('.')
    frac_str = (frac_str + "000")[:3]  # 소수부 3자리 맞추기

    return int(sec_str) * 1000 + int(frac_str)