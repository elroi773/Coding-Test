def solution(n, computers):
    visited = [False] * n
    answer = 0

    def dfs(cur):
        visited[cur] = True
        for nxt in range(n):
            if computers[cur][nxt] == 1 and not visited[nxt]:
                dfs(nxt)

    for i in range(n):
        if not visited[i]:
            answer += 1
            dfs(i)

    return answer