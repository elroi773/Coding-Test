def solution(k, m, score):
    # 1. 사과 점수를 내림차순으로 정렬
    score.sort(reverse=True)

    total = 0

    # 2. m개씩 묶어서 상자 단위로 판매
    for i in range(0, len(score) - len(score) % m, m):
        box = score[i:i + m]           # 사과 m개로 구성된 상자
        total += min(box) * m          # 최저 점수 * m

    return total
