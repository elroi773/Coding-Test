def solution(play_time, adv_time, logs):
    play_sec = to_sec(play_time)
    adv_sec = to_sec(adv_time)

    if adv_sec >= play_sec:
        return "00:00:00"

    # diff[t] = t초에서 시청자 수 변화량
    diff = [0] * (play_sec + 2)

    # [start, end) 로 처리
    for log in logs:
        s, e = log.split('-')
        s = to_sec(s)
        e = to_sec(e)
        diff[s] += 1
        diff[e] -= 1

    # prefix_watch[t] = 0초 ~ (t-1)초까지 누적 재생시간(초 합)
    prefix_watch = [0] * (play_sec + 2)
    viewers = 0

    for t in range(play_sec):
        viewers += diff[t]
        prefix_watch[t + 1] = prefix_watch[t] + viewers

    best_start = 0
    best_value = -1

    for start in range(0, play_sec - adv_sec + 1):
        end = start + adv_sec
        value = prefix_watch[end] - prefix_watch[start]
        if value > best_value:
            best_value = value
            best_start = start
        # 같으면 더 빠른 best_start 유지(갱신 X)

    return to_time(best_start)


def to_sec(t: str) -> int:
    h = int(t[0:2])
    m = int(t[3:5])
    s = int(t[6:8])
    return h * 3600 + m * 60 + s


def to_time(sec: int) -> str:
    h = sec // 3600
    m = (sec % 3600) // 60
    s = sec % 60
    return f"{h:02d}:{m:02d}:{s:02d}"