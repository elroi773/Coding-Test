import math

def solution(r1, r2):
    R1sq = r1 * r1
    R2sq = r2 * r2
    answer = 0

    for x in range(0, r2 + 1):
        xx = x * x

        # 바깥 원(r2)에서의 최대 y (floor sqrt)
        maxY = math.isqrt(R2sq - xx)

        # 안쪽 원(r1)에서의 최소 y (ceil sqrt)
        diff1 = R1sq - xx
        if diff1 > 0:
            y = math.isqrt(diff1)
            if y * y < diff1:  # isqrt는 floor라서 필요하면 +1
                y += 1
            minY = y
        else:
            minY = 0

        if maxY < minY:
            continue  # 이 x에서는 유효한 y 없음

        if x == 0:
            # y축 위: (0, y), (0, -y) → y 하나당 2개
            answer += 2 * (maxY - minY + 1)
        else:
            if minY == 0:
                # y = 0: (±x, 0) → 2개
                answer += 2
                # y >= 1: (±x, ±y) → y 하나당 4개
                answer += 4 * maxY
            else:
                # y >= minY: (±x, ±y) → y 하나당 4개
                answer += 4 * (maxY - minY + 1)

    return answer