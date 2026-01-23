from collections import deque

def solution(rectangle, characterX, characterY, itemX, itemY):
    MAX = 102  # 1~50 -> *2 하면 2~100, 여유 포함
    board = [[0] * MAX for _ in range(MAX)]

    # 1) 모든 직사각형 영역을 1로 채우기 (겹치는 곳도 포함)
    for x1, y1, x2, y2 in rectangle:
        x1 *= 2; y1 *= 2; x2 *= 2; y2 *= 2
        for x in range(x1, x2 + 1):
            for y in range(y1, y2 + 1):
                board[x][y] = 1

    # 2) 각 직사각형 내부를 0으로 지우기 (테두리만 남김)
    for x1, y1, x2, y2 in rectangle:
        x1 *= 2; y1 *= 2; x2 *= 2; y2 *= 2
        for x in range(x1 + 1, x2):
            for y in range(y1 + 1, y2):
                board[x][y] = 0

    # 3) BFS: 테두리(board==1)만 따라 이동
    sx, sy = characterX * 2, characterY * 2
    tx, ty = itemX * 2, itemY * 2

    q = deque([(sx, sy, 0)])
    visited = [[False] * MAX for _ in range(MAX)]
    visited[sx][sy] = True

    dirs = [(1, 0), (-1, 0), (0, 1), (0, -1)]

    while q:
        x, y, d = q.popleft()
        if (x, y) == (tx, ty):
            return d // 2  # 2배 좌표 거리 -> 원래 거리

        for dx, dy in dirs:
            nx, ny = x + dx, y + dy
            if 0 <= nx < MAX and 0 <= ny < MAX and not visited[nx][ny] and board[nx][ny] == 1:
                visited[nx][ny] = True
                q.append((nx, ny, d + 1))

    return 0  # 문제 조건상 도달 가능