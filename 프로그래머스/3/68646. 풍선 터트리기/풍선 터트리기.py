def solution(a):
    n = len(a)
    INF = 10**18

    # right_min[i] = min(a[i+1], a[i+2], ..., a[n-1])  (i의 오른쪽 구간 최소)
    right_min = [INF] * n
    cur = INF
    for i in range(n - 1, -1, -1):
        right_min[i] = cur
        if a[i] < cur:
            cur = a[i]

    # 왼쪽 최소값을 스캔하며 조건 체크
    ans = 0
    left_min = INF
    for i in range(n):
        if a[i] <= left_min or a[i] <= right_min[i]:
            ans += 1
        if a[i] < left_min:
            left_min = a[i]

    return ans