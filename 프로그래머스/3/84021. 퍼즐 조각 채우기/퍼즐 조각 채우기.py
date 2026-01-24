from collections import deque, Counter

def solution(game_board, table):
    n = len(game_board)
    dr = (1, -1, 0, 0)
    dc = (0, 0, 1, -1)

    def extract(grid, target):
        visited = [[False]*n for _ in range(n)]
        comps = []
        for r in range(n):
            for c in range(n):
                if visited[r][c] or grid[r][c] != target:
                    continue
                visited[r][c] = True
                q = deque([(r, c)])
                pts = []
                while q:
                    cr, cc = q.popleft()
                    pts.append((cr, cc))
                    for k in range(4):
                        nr, nc = cr + dr[k], cc + dc[k]
                        if 0 <= nr < n and 0 <= nc < n and not visited[nr][nc] and grid[nr][nc] == target:
                            visited[nr][nc] = True
                            q.append((nr, nc))
                comps.append(pts)
        return comps

    def normalize(pts):
        minr = min(r for r, _ in pts)
        minc = min(c for _, c in pts)
        norm = [(r - minr, c - minc) for r, c in pts]
        norm.sort()
        return norm

    # (r,c) -> (c, -r)
    def rotate90(pts):
        return [(c, -r) for r, c in pts]

    def canonical_key(raw_pts):
        pts = list(raw_pts)
        best = None
        for _ in range(4):
            norm = normalize(pts)
            key = tuple(norm)
            if best is None or key < best:
                best = key
            # 다음 회전은 "정규화된 좌표" 기준으로
            pts = rotate90(norm)
        return best

    blocks = extract(table, 1)
    block_count = Counter(canonical_key(b) for b in blocks)

    holes = extract(game_board, 0)
    filled = 0
    for h in holes:
        hk = canonical_key(h)
        if block_count[hk] > 0:
            block_count[hk] -= 1
            filled += len(h)

    return filled