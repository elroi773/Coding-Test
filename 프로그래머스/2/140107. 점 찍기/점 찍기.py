def solution(k, d):
    answer = 0
    for x in range(0, d + 1, k):
        max_y = int((d**2 - x**2)**0.5)  # 가능한 y의 최대 거리
        answer += (max_y // k) + 1       # 0 포함이므로 +1
    return answer
