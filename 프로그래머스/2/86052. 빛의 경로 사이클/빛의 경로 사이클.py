def solution(grid):
    # 이동 방향: 상, 우, 하, 좌
    dx = [-1, 0, 1, 0]
    dy = [0, 1, 0, -1]
    n, m = len(grid), len(grid[0])
    visited = [[[False]*4 for _ in range(m)] for _ in range(n)]
    answer = []

    for i in range(n):
        for j in range(m):
            for d in range(4):
                if not visited[i][j][d]:
                    x, y, dir = i, j, d
                    count = 0
                    
                    while not visited[x][y][dir]:
                        visited[x][y][dir] = True
                        count += 1

                        # 다음 위치 계산 (토러스)
                        x = (x + dx[dir]) % n
                        y = (y + dy[dir]) % m

                        # 방향 변경
                        if grid[x][y] == 'L':
                            dir = (dir + 3) % 4
                        elif grid[x][y] == 'R':
                            dir = (dir + 1) % 4
                        # 'S'는 방향 그대로 유지

                    answer.append(count)

    return sorted(answer)
