def solution(tickets):
    # 1) 그래프 구성
    g = {}
    for a, b in tickets:
        g.setdefault(a, []).append(b)

    # 2) 내림차순 정렬 후 pop() => 실제 선택은 오름차순(사전순) 우선
    for a in g:
        g[a].sort(reverse=True)

    # 3) Hierholzer (스택)
    stack = ["ICN"]
    route = []

    while stack:
        cur = stack[-1]
        if cur in g and g[cur]:
            stack.append(g[cur].pop())
        else:
            route.append(stack.pop())

    return route[::-1]