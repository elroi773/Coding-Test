def solution(n, m, section):
    answer = 0
    painted = -1  # 마지막으로 칠한 구역의 끝 위치
    
    for s in section:
        if s > painted:       # 현재 구역이 아직 안 칠해졌다면
            painted = s + m - 1  # s부터 m칸을 칠함
            answer += 1         # 칠한 횟수 +1
    return answer
