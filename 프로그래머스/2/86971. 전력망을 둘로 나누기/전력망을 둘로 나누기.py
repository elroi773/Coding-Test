def solution(n, wires):
    # 그래프(인접 리스트) 구성
    graph = {i: [] for i in range(1, n + 1)}
    for a, b in wires:
        graph[a].append(b)
        graph[b].append(a)

    def dfs(node, visited):
        visited.add(node)
        count = 1
        for next_node in graph[node]:
            if next_node not in visited:
                count += dfs(next_node, visited)
        return count

    min_diff = n  # 가능한 최대 차이로 초기화

    # 각 간선을 하나씩 끊어보기
    for a, b in wires:
        # 간선 끊기
        graph[a].remove(b)
        graph[b].remove(a)

        # DFS로 한쪽 전력망의 송전탑 개수 세기
        visited = set()
        count_a = dfs(a, visited)
        count_b = n - count_a
        diff = abs(count_a - count_b)

        min_diff = min(min_diff, diff)

        # 간선 복원
        graph[a].append(b)
        graph[b].append(a)

    return min_diff
