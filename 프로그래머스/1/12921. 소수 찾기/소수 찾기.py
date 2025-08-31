def solution(n):
    # n+1 크기의 배열을 True로 초기화
    is_prime = [True] * (n + 1)
    is_prime[0] = False
    is_prime[1] = False

    # 2부터 sqrt(n)까지 배수 제거
    for i in range(2, int(n**0.5) + 1):
        if is_prime[i]:
            for j in range(i*i, n+1, i):
                is_prime[j] = False

    # True인 값(소수) 개수 세기
    return sum(is_prime)
