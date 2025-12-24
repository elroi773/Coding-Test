import sys
sys.setrecursionlimit(10**7)

def solution(nodes, edges):
    from collections import defaultdict, deque

    n = len(nodes)
    graph = defaultdict(list)

    for a, b in edges:
        graph[a].append(b)
        graph[b].append(a)

    visited = set()
    odd_even_tree = 0
    reverse_tree = 0

    for start in nodes:
        if start in visited:
            continue

        queue = deque([start])
        visited.add(start)
        component = []

        while queue:
            cur = queue.popleft()
            component.append(cur)
            for nxt in graph[cur]:
                if nxt not in visited:
                    visited.add(nxt)
                    queue.append(nxt)

        A = B = 0
        for v in component:
            degree = len(graph[v])
            if degree % 2 == v % 2:
                A += 1
            else:
                B += 1

        if A == 1:
            odd_even_tree += 1
        if B == 1:
            reverse_tree += 1

    return [odd_even_tree, reverse_tree]
