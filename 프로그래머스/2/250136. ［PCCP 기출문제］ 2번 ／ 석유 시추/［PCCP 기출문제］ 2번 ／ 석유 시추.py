def solution(land):
    n = len(land)        # 세로
    m = len(land[0])     # 가로

    visited = [[False] * m for _ in range(n)]
    col_total = [0] * m  # 각 열마다 얻을 수 있는 석유량
    directions = [(1, 0), (-1, 0), (0, 1), (0, -1)]

    for i in range(n):
        for j in range(m):
            # 아직 방문하지 않은 석유 칸이면 새로운 덩어리 탐색 시작
            if land[i][j] == 1 and not visited[i][j]:
                stack = [(i, j)]
                visited[i][j] = True

                size = 0          # 이 덩어리의 전체 크기
                cols = set()      # 이 덩어리가 포함하는 열 인덱스들

                # DFS로 연결된 모든 석유 칸 탐색
                while stack:
                    x, y = stack.pop()
                    size += 1
                    cols.add(y)   # 열 기록

                    for dx, dy in directions:
                        nx, ny = x + dx, y + dy
                        if 0 <= nx < n and 0 <= ny < m:
                            if not visited[nx][ny] and land[nx][ny] == 1:
                                visited[nx][ny] = True
                                stack.append((nx, ny))

                # 이 덩어리가 지나가는 각 열마다 덩어리 크기를 더한다
                for c in cols:
                    col_total[c] += size

    return max(col_total)