def solution(n, cores):
    m = len(cores)
    
    # 처음 시작할 때 각 코어가 하나씩 맡음
    if n <= m:
        return n
    
    # t시간까지 처리 가능한 작업 수
    def count_jobs(t):
        return sum(t // core + 1 for core in cores)
    
    left, right = 0, max(cores) * n
    
    # n개 이상 처리 가능한 최소 시간 찾기
    while left < right:
        mid = (left + right) // 2
        if count_jobs(mid) >= n:
            right = mid
        else:
            left = mid + 1
    
    time = left
    
    # time-1까지 처리한 작업 수
    done = count_jobs(time - 1)
    
    # time 시점에 작업이 끝난 코어들 순서대로 확인
    for i, core in enumerate(cores):
        if time % core == 0:
            done += 1
            if done == n:
                return i + 1