from itertools import permutations

def solution(n, weak, dist):
    m = len(weak)
    # 원형을 일자로 펴기 위해 weak 확장
    extended = weak + [w + n for w in weak]

    best = float('inf')

    # 시작 취약점을 i로 잡고, i부터 m개를 커버하는 문제로 변환
    for start in range(m):
        segment = extended[start:start + m]  # 점검해야 할 취약점들(선형)

        # 친구 투입 순서 모든 경우 시도
        for order in permutations(dist):
            used = 1  # 첫 친구부터 시작
            # 현재 친구가 커버 가능한 끝 위치
            coverage_end = segment[0] + order[0]

            for idx in range(m):
                # 현재 취약점이 커버 범위를 넘어가면 다음 친구 투입
                if segment[idx] > coverage_end:
                    used += 1
                    if used > len(dist):
                        break
                    coverage_end = segment[idx] + order[used - 1]

            if used <= len(dist):
                best = min(best, used)

    return -1 if best == float('inf') else best