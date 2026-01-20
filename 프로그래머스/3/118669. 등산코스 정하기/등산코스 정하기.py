import heapq

def solution(n, paths, gates, summits):
    g = [[] for _ in range(n + 1)]
    for a, b, w in paths:
        g[a].append((b, w))
        g[b].append((a, w))

    is_gate = [False] * (n + 1)
    is_summit = [False] * (n + 1)
    for x in gates: is_gate[x] = True
    for x in summits: is_summit[x] = True

    INF = 10**18
    dist = [INF] * (n + 1)
    pq = []

    for gate in gates:
        dist[gate] = 0
        heapq.heappush(pq, (0, gate))

    best_intensity = INF

    while pq:
        inten, u = heapq.heappop(pq)
        if inten != dist[u]:
            continue
        if inten > best_intensity:
            break

        if is_summit[u]:
            best_intensity = min(best_intensity, inten)
            continue

        for v, w in g[u]:
            if is_gate[v]:
                continue
            ni = max(inten, w)
            if ni < dist[v]:
                dist[v] = ni
                heapq.heappush(pq, (ni, v))

    summits.sort()
    ans_s = summits[0]
    ans_i = dist[ans_s]
    for s in summits:
        if dist[s] < ans_i:
            ans_i = dist[s]
            ans_s = s

    return [ans_s, ans_i]