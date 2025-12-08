from collections import deque

def solution(storage, requests):
    n = len(storage)
    m = len(storage[0])

    # 창고를 2차원 리스트로 변환, 빈 칸은 '.' 로 표현
    grid = [list(row) for row in storage]

    # 4방향 이동
    dirs = [(-1, 0), (1, 0), (0, -1), (0, 1)]

    for req in requests:
        target = req[0]

        # 크레인: 해당 알파벳 전부 제거
        if len(req) == 2:
            for i in range(n):
                for j in range(m):
                    if grid[i][j] == target:
                        grid[i][j] = '.'
            continue

        # 지게차: "외부와 연결된 빈 칸"을 BFS로 탐색 (확장 격자 사용)
        H, W = n + 2, m + 2
        visited = [[False] * W for _ in range(H)]
        q = deque()

        # (0,0)에서 시작: 완전히 바깥 영역
        visited[0][0] = True
        q.append((0, 0))

        while q:
            r, c = q.popleft()
            for dr, dc in dirs:
                nr, nc = r + dr, c + dc
                if not (0 <= nr < H and 0 <= nc < W):
                    continue
                if visited[nr][nc]:
                    continue

                # (nr,nc)가 확장 격자에서 안쪽인지(실제 창고 범위) 바깥인지 확인
                if 1 <= nr <= n and 1 <= nc <= m:
                    # 실제 창고 안쪽: 빈 칸이면 이동 가능
                    if grid[nr - 1][nc - 1] == '.':
                        visited[nr][nc] = True
                        q.append((nr, nc))
                else:
                    # 창고 밖: 항상 이동 가능 (외부 공간)
                    visited[nr][nc] = True
                    q.append((nr, nc))

        # 접근 가능한(외부와 연결된 빈칸에 인접한) target 컨테이너만 제거
        to_remove = []
        for i in range(n):
            for j in range(m):
                if grid[i][j] != target:
                    continue
                er, ec = i + 1, j + 1  # 확장 격자 기준 좌표
                for dr, dc in dirs:
                    nr, nc = er + dr, ec + dc
                    if 0 <= nr < H and 0 <= nc < W and visited[nr][nc]:
                        to_remove.append((i, j))
                        break

        for i, j in to_remove:
            grid[i][j] = '.'

    # 남은 컨테이너 개수 세기
    remaining = sum(1 for i in range(n) for j in range(m) if grid[i][j] != '.')
    return remaining