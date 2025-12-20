import heapq

def solution(N, road, K):
    # 1. 그래프 생성
    graph = [[] for _ in range(N + 1)]
    for a, b, c in road:
        graph[a].append((b, c))
        graph[b].append((a, c))
    
    # 2. 다익스트라 초기화
    INF = float('inf')
    dist = [INF] * (N + 1)
    dist[1] = 0
    
    pq = []
    heapq.heappush(pq, (0, 1))  # (거리, 마을)
    
    # 3. 다익스트라 수행
    while pq:
        cur_dist, cur_node = heapq.heappop(pq)
        
        if cur_dist > dist[cur_node]:
            continue
        
        for next_node, cost in graph[cur_node]:
            new_dist = cur_dist + cost
            if new_dist < dist[next_node]:
                dist[next_node] = new_dist
                heapq.heappush(pq, (new_dist, next_node))
    
    # 4. K 이하로 도달 가능한 마을 수 세기
    answer = sum(1 for i in range(1, N + 1) if dist[i] <= K)
    
    return answer
