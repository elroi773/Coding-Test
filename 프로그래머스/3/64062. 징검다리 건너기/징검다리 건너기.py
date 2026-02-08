def solution(stones, k):
    # m명이 건널 수 있는지 판별
    # stones[i] < m 이면 m번째 사람 기준으로 그 돌은 0 이하가 되어 밟을 수 없음
    # 밟을 수 없는 돌이 연속 k개 이상이면 실패
    def can_cross(m):
        consecutive = 0
        for s in stones:
            if s < m:
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
            answer = mid
            left = mid + 1
        else:
            right = mid - 1

    return answer