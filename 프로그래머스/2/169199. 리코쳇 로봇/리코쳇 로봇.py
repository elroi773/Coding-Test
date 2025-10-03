from collections import deque

def solution(board):
    n, m = len(board), len(board[0])  # 세로, 가로
    start, goal = None, None

    # 시작점과 목표점 찾기
    for i in range(n):
        for j in range(m):
            if board[i][j] == "R":
                start = (i, j)
            elif board[i][j] == "G":
                goal = (i, j)

    # BFS
    q = deque()
    q.append((start[0], start[1], 0))  # (x, y, 이동 횟수)
    visited = [[False] * m for _ in range(n)]
    visited[start[0]][start[1]] = True

    directions = [(-1,0),(1,0),(0,-1),(0,1)]  # 상하좌우

    while q:
        x, y, cnt = q.popleft()
        if (x, y) == goal:
            return cnt

        for dx, dy in directions:
            nx, ny = x, y
            # 끝까지 미끄러지기
            while True:
                tx, ty = nx + dx, ny + dy
                if not (0 <= tx < n and 0 <= ty < m):  # 보드 밖
                    break
                if board[tx][ty] == "D":  # 장애물
                    break
                nx, ny = tx, ty

            # 멈춘 지점
            if not visited[nx][ny]:
                visited[nx][ny] = True
                q.append((nx, ny, cnt + 1))

    return -1
