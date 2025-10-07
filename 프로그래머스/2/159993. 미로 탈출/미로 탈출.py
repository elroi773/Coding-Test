from collections import deque

def solution(maps):
    n = len(maps)
    m = len(maps[0])

    # 시작, 레버, 출구 좌표 찾기
    for i in range(n):
        for j in range(m):
            if maps[i][j] == 'S':
                start = (i, j)
            elif maps[i][j] == 'L':
                lever = (i, j)
            elif maps[i][j] == 'E':
                exit = (i, j)

    # BFS 함수: 시작점 -> 목표까지 최소 거리, 이동 불가 시 -1
    def bfs(start, target):
        visited = [[False]*m for _ in range(n)]
        queue = deque()
        queue.append((start[0], start[1], 0))
        visited[start[0]][start[1]] = True

        directions = [(1,0), (-1,0), (0,1), (0,-1)]

        while queue:
            x, y, dist = queue.popleft()
            if (x, y) == target:
                return dist
            for dx, dy in directions:
                nx, ny = x + dx, y + dy
                if 0 <= nx < n and 0 <= ny < m and not visited[nx][ny] and maps[nx][ny] != 'X':
                    visited[nx][ny] = True
                    queue.append((nx, ny, dist + 1))
        return -1

    dist_to_lever = bfs(start, lever)
    if dist_to_lever == -1:
        return -1

    dist_to_exit = bfs(lever, exit)
    if dist_to_exit == -1:
        return -1

    return dist_to_lever + dist_to_exit

