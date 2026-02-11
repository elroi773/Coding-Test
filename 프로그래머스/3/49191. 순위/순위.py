def solution(n, results):
    # win[a][b] = a가 b를 이김(알려짐/추론됨)
    win = [[False] * (n + 1) for _ in range(n + 1)]

    for a, b in results:
        win[a][b] = True

    # Floyd-Warshall (전이 관계: a>k and k>b => a>b)
    for k in range(1, n + 1):
        for a in range(1, n + 1):
            if not win[a][k]:
                continue
            row_a = win[a]
            row_k = win[k]
            for b in range(1, n + 1):
                if row_k[b]:
                    row_a[b] = True

    answer = 0
    for i in range(1, n + 1):
        known = 0
        for j in range(1, n + 1):
            if i == j:
                continue
            if win[i][j] or win[j][i]:
                known += 1
        if known == n - 1:
            answer += 1

    return answer