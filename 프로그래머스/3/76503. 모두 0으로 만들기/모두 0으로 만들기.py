def solution(a, edges):
    n = len(a)
    if sum(a) != 0:
        return -1

    # 인접 리스트
    g = [[] for _ in range(n)]
    for u, v in edges:
        g[u].append(v)
        g[v].append(u)

    # 0을 루트로 잡고 parent + 방문 순서(전위)를 만든 뒤 역순으로 후위 처리
    parent = [-1] * n
    order = []
    stack = [0]
    parent[0] = 0

    while stack:
        u = stack.pop()
        order.append(u)
        for v in g[u]:
            if parent[v] == -1:
                parent[v] = u
                stack.append(v)

    ans = 0
    # 후위(leaf -> root): 루트 제외하고 처리
    for u in reversed(order[1:]):
        ans += abs(a[u])
        p = parent[u]
        a[p] += a[u]
        a[u] = 0

    # sum(a)==0 이었으니 최종적으로 루트도 0이어야 함
    return ans