def solution(n, lighthouse):
    # 인접 리스트
    g = [[] for _ in range(n + 1)]
    for a, b in lighthouse:
        g[a].append(b)
        g[b].append(a)

    parent = [0] * (n + 1)
    order = []
    stack = [1]
    parent[1] = -1

    # 반복 DFS로 parent 설정 + 방문 순서(order) 저장
    while stack:
        u = stack.pop()
        order.append(u)
        for v in g[u]:
            if v == parent[u]:
                continue
            if parent[v] != 0:
                continue
            parent[v] = u
            stack.append(v)

    # dp0[u]: u 끔(선택X), dp1[u]: u 켬(선택O)
    dp0 = [0] * (n + 1)
    dp1 = [0] * (n + 1)

    # 후위 순회 효과: order 역순으로 DP
    for u in reversed(order):
        off = 0   # u를 끄면, 자식은 반드시 켜야 간선이 커버됨
        on = 1    # u를 켜면 1개 추가, 자식은 min 선택
        for v in g[u]:
            if v == parent[u]:
                continue
            off += dp1[v]
            on += min(dp0[v], dp1[v])
        dp0[u] = off
        dp1[u] = on

    return min(dp0[1], dp1[1])