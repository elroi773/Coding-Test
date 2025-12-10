def solution(diffs, times, limit):
    n = len(diffs)

    # 특정 level로 퍼즐을 모두 풀 수 있는지 확인하는 함수
    def can_clear(level):
        total = 0

        # 첫 퍼즐은 이전 퍼즐이 없으므로 무조건 times[0]
        total += times[0]
        if total > limit:
            return False

        for i in range(1, n):
            diff = diffs[i]
            time_cur = times[i]
            time_prev = times[i-1]

            if level >= diff:
                total += time_cur
            else:
                fail = diff - level
                total += fail * (time_cur + time_prev) + time_cur

            if total > limit:
                return False

        return True

    # level은 1 ~ max(diffs) 사이에서 이분 탐색
    left, right = 1, max(diffs)
    answer = right

    while left <= right:
        mid = (left + right) // 2

        if can_clear(mid):
            answer = mid
            right = mid - 1
        else:
            left = mid + 1

    return answer
