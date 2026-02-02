import heapq

def solution(board):
    n = len(board)
    INF = 10**15

    # dir: 0=up, 1=down, 2=left, 3=right
    dx = (-1, 1, 0, 0)
    dy = (0, 0, -1, 1)

    # dist[x][y][dir] = (x,y)에 dir 방향으로 "들어온" 최소 비용
    dist = [[[INF] * 4 for _ in range(n)] for __ in range(n)]

    # (cost, x, y, dir)  dir=-1은 시작(이전 방향 없음)
    pq = [(0, 0, 0, -1)]

    while pq:
        cost, x, y, d = heapq.heappop(pq)

        for nd in range(4):
            nx, ny = x + dx[nd], y + dy[nd]
            if not (0 <= nx < n and 0 <= ny < n):
                continue
            if board[nx][ny] == 1:
                continue

            add = 100 if d == -1 or d == nd else 600  # 직선 100, 코너 600(100+500)
            new_cost = cost + add

            if new_cost < dist[nx][ny][nd]:
                dist[nx][ny][nd] = new_cost
                heapq.heappush(pq, (new_cost, nx, ny, nd))

    return min(dist[n - 1][n - 1])