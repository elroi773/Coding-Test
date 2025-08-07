def solution(answers):
    one = [1, 2, 3, 4, 5]
    two = [2, 1, 2, 3, 2, 4, 2, 5]
    three = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]

    scores = [0, 0, 0]  # 수포자 1, 2, 3의 정답 수

    for i in range(len(answers)):
        if answers[i] == one[i % len(one)]:
            scores[0] += 1
        if answers[i] == two[i % len(two)]:
            scores[1] += 1
        if answers[i] == three[i % len(three)]:
            scores[2] += 1

    max_score = max(scores)

    result = []
    for i in range(3):
        if scores[i] == max_score:
            result.append(i + 1)  # 수포자 번호는 1번부터 시작

    return result
