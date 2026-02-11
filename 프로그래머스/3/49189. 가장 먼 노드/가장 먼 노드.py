from collections import deque

def solution(n, edge):
    # 1) 인접 리스트
    g = [[] for _ in range(n + 1)]
    for a, b in edge:
        g[a].append(b)
        g[b].append(a)

    # 2) 거리 배열 (-1: 미방문)
    dist = [-1] * (n + 1)
    dist[1] = 0

    # 3) BFS
    q = deque([1])
    while q:
        cur = q.popleft()
        for nxt in g[cur]:
            if dist[nxt] == -1:
                dist[nxt] = dist[cur] + 1
                q.append(nxt)

    # 4) 최대 거리와 그 개수
    max_dist = max(dist[1:])      # 0번 인덱스 제외
    return dist.count(max_dist)