def solution(elements):
    n = len(elements)
    # 원형 처리를 위해 배열 2번 이어 붙이기
    arr = elements * 2

    # 누적합(prefix sum) 배열 만들기
    prefix = [0] * (2 * n + 1)
    for i in range(2 * n):
        prefix[i + 1] = prefix[i] + arr[i]

    sums = set()

    # 부분 수열 길이 1 ~ n
    for length in range(1, n + 1):
        # 시작 인덱스 0 ~ n-1까지만 보면 원형의 모든 경우를 다 커버
        for start in range(n):
            total = prefix[start + length] - prefix[start]
            sums.add(total)

    return len(sums)
