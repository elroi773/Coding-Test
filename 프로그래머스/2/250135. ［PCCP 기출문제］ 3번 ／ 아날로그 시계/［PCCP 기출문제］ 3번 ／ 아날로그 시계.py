def solution(h1, m1, s1, h2, m2, s2):
    # 초 단위로 변환
    start = h1 * 3600 + m1 * 60 + s1
    end = h2 * 3600 + m2 * 60 + s2

    # [start, end]에서의 겹침 횟수를 구하는 함수 (초침-다른 바늘 한 쌍)
    # Δω/360 = num/den 일 때, F(t) = floor(num * t / den)
    def pair_count_closed(start, end, num, den):
        F_end = (num * end) // den
        F_start = (num * start) // den
        cnt = F_end - F_start
        # start 시각이 정확히 겹치는 순간이면 포함
        if (num * start) % den == 0:
            cnt += 1
        return cnt

    # 초침-분침 (Δω/360 = 59 / 3600)
    cnt_sm = pair_count_closed(start, end, 59, 3600)
    # 초침-시침 (Δω/360 = 719 / 43200)
    cnt_sh = pair_count_closed(start, end, 719, 43200)

    total = cnt_sm + cnt_sh

    # 세 바늘이 동시에 겹치는 시각: 0초, 43200초
    triple = 0
    for t in (0, 43200):
        if start <= t <= end:
            triple += 1

    # triple 만큼 1씩 빼줌 (각각에서 2번 세었으니 실제는 1번)
    return total - triple