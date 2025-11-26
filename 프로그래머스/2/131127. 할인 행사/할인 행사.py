from collections import Counter

def solution(want, number, discount):
    answer = 0

    # 1. 원하는 상품과 수량을 Counter로 저장
    want_counter = Counter()
    for w, n in zip(want, number):
        want_counter[w] = n

    # 2. discount 배열에서 10일씩 확인
    for i in range(len(discount) - 9):
        window = discount[i:i+10]  # 10일 구간
        window_counter = Counter(window)
        if window_counter == want_counter:
            answer += 1

    return answer
