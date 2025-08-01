def solution(k, score):
    hall_of_fame = []
    answer = []

    for s in score:
        hall_of_fame.append(s)
        hall_of_fame.sort(reverse=True)  # 내림차순 정렬
        if len(hall_of_fame) > k:
            hall_of_fame.pop()  # k개 넘으면 가장 낮은 점수 제거
        answer.append(hall_of_fame[-1])  # 현재 명예의 전당의 최하위 점수 기록

    return answer
