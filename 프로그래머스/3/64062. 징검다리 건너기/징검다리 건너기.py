def solution(stones, k):
    # m명이 건널 수 있는지 체크
    def can_cross(m):
        consecutive = 0
        for s in stones:
            if s < m:               # m명이 지나가면 0 이하가 되는 돌(밟기 불가)
                consecutive += 1
                if consecutive >= k:
                    return False
            else:
                consecutive = 0
        return True

    left, right = 1, max(stones)
    answer = 0

    while left <= right:
        mid = (left + right) // 2
        if can_cross(mid):
            answer = mid          # mid명 가능 -> 더 큰 값 시도
            left = mid + 1
        else:
            right = mid - 1       # mid명 불가능 -> 더 작은 값으로
    return answer