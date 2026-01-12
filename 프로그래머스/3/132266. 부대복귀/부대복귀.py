from collections import deque

def solution(n, roads, sources, destination):
    # 1) 인접 리스트
    graph = [[] for _ in range(n + 1)]
    for a, b in roads:
        graph[a].append(b)
        graph[b].append(a)

    # 2) destination 기준 BFS
    dist = [-1] * (n + 1)
    dist[destination] = 0
    q = deque([destination])

    while q:
        cur = q.popleft()
        nd = dist[cur] + 1
        for nxt in graph[cur]:
            if dist[nxt] == -1:
                dist[nxt] = nd
                q.append(nxt)

    # 3) sources 순서대로 결과
    return [dist[s] for s in sources]