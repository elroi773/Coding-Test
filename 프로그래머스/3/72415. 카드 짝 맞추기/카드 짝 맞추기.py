from collections import deque

def solution(board, r, c):
    N = 4
    dr = (-1, 1, 0, 0)
    dc = (0, 0, -1, 1)

    def idx(rr, cc): return rr * 4 + cc
    def in_range(rr, cc): return 0 <= rr < 4 and 0 <= cc < 4

    # 남은 카드 마스크 + 각 카드(1~6)의 두 위치 저장
    mask = 0
    pos = [[] for _ in range(7)]
    for i in range(4):
        for j in range(4):
            v = board[i][j]
            if v != 0:
                p = idx(i, j)
                mask |= (1 << p)
                pos[v].append(p)

    types = [v for v in range(1, 7) if len(pos[v]) == 2]

    def ctrl_move(from_idx, d, cur_mask):
        rr, cc = divmod(from_idx, 4)
        while True:
            nr, nc = rr + dr[d], cc + dc[d]
            if not in_range(nr, nc):
                break
            rr, cc = nr, nc
            ni = idx(rr, cc)
            if cur_mask & (1 << ni):
                return ni
        return idx(rr, cc)

    def step_move(from_idx, d):
        rr, cc = divmod(from_idx, 4)
        nr, nc = rr + dr[d], cc + dc[d]
        if not in_range(nr, nc):
            return from_idx
        return idx(nr, nc)

    def dist(start, target, cur_mask):
        if start == target:
            return 0
        q = deque([start])
        vis = [-1] * 16
        vis[start] = 0
        while q:
            u = q.popleft()
            du = vis[u]
            for d in range(4):
                v1 = step_move(u, d)
                if vis[v1] == -1:
                    vis[v1] = du + 1
                    if v1 == target:
                        return vis[v1]
                    q.append(v1)
                v2 = ctrl_move(u, d, cur_mask)
                if vis[v2] == -1:
                    vis[v2] = du + 1
                    if v2 == target:
                        return vis[v2]
                    q.append(v2)
        return vis[target]

    memo = {}

    def dfs(cur_mask, cursor):
        if cur_mask == 0:
            return 0
        key = (cur_mask << 4) | cursor
        if key in memo:
            return memo[key]

        best = 10**9
        for t in types:
            p1, p2 = pos[t]
            if (cur_mask & (1 << p1)) == 0 and (cur_mask & (1 << p2)) == 0:
                continue

            next_mask = cur_mask & ~(1 << p1) & ~(1 << p2)

            # 순서 1: cursor -> p1(Enter) -> p2(Enter)
            cost1 = dist(cursor, p1, cur_mask) + 1 + dist(p1, p2, cur_mask) + 1
            best = min(best, cost1 + dfs(next_mask, p2))

            # 순서 2: cursor -> p2(Enter) -> p1(Enter)
            cost2 = dist(cursor, p2, cur_mask) + 1 + dist(p2, p1, cur_mask) + 1
            best = min(best, cost2 + dfs(next_mask, p1))

        memo[key] = best
        return best

    return dfs(mask, idx(r, c))