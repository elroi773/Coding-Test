def solution(n, bans):
    # 26의 거듭제곱 미리 계산
    pow26 = [1]
    for _ in range(11):
        pow26.append(pow26[-1] * 26)

    # 문자열의 순서 번호 계산
    def rank(s):
        L = len(s)
        r = 0

        # 더 짧은 문자열
        for l in range(1, L):
            r += pow26[l]

        # 같은 길이에서의 사전순
        for i, c in enumerate(s):
            r += (ord(c) - ord('a')) * pow26[L - i - 1]

        return r + 1  # 1-based

    # 순서 번호 → 문자열
    def unrank(k):
        L = 1
        while k > pow26[L]:
            k -= pow26[L]
            L += 1

        k -= 1  # 0-based
        res = []
        for i in range(L):
            div = pow26[L - i - 1]
            res.append(chr(ord('a') + k // div))
            k %= div
        return ''.join(res)

    # bans를 순서 번호로 변환
    banned_ranks = sorted(rank(b) for b in bans)

    target = n
    for r in banned_ranks:
        if r <= target:
            target += 1
        else:
            break

    return unrank(target)
