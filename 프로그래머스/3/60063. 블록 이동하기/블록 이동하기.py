from collections import deque

def solution(board):
    n = len(board)

    # padding with walls to simplify bounds checks
    b = [[1] * (n + 2) for _ in range(n + 2)]
    for i in range(n):
        for j in range(n):
            b[i + 1][j + 1] = board[i][j]

    def norm(r1, c1, r2, c2):
        # ensure (r1,c1) <= (r2,c2) lexicographically
        if (r1, c1) <= (r2, c2):
            return (r1, c1, r2, c2)
        return (r2, c2, r1, c1)

    def is_goal(s):
        r1, c1, r2, c2 = s
        return (r1 == n and c1 == n) or (r2 == n and c2 == n)

    start = norm(1, 1, 1, 2)
    q = deque([(start, 0)])
    visited = {start}

    moves = [(-1, 0), (1, 0), (0, -1), (0, 1)]

    while q:
        (r1, c1, r2, c2), t = q.popleft()
        if is_goal((r1, c1, r2, c2)):
            return t

        # 1) move 4 directions
        for dr, dc in moves:
            nr1, nc1 = r1 + dr, c1 + dc
            nr2, nc2 = r2 + dr, c2 + dc
            if b[nr1][nc1] == 0 and b[nr2][nc2] == 0:
                nxt = norm(nr1, nc1, nr2, nc2)
                if nxt not in visited:
                    visited.add(nxt)
                    q.append((nxt, t + 1))

        # 2) rotations
        # horizontal
        if r1 == r2:
            for d in (-1, 1):  # up / down
                if b[r1 + d][c1] == 0 and b[r2 + d][c2] == 0:
                    # pivot at (r1,c1)
                    nxt1 = norm(r1, c1, r1 + d, c1)
                    if nxt1 not in visited:
                        visited.add(nxt1)
                        q.append((nxt1, t + 1))
                    # pivot at (r2,c2)
                    nxt2 = norm(r2, c2, r2 + d, c2)
                    if nxt2 not in visited:
                        visited.add(nxt2)
                        q.append((nxt2, t + 1))

        # vertical
        if c1 == c2:
            for d in (-1, 1):  # left / right
                if b[r1][c1 + d] == 0 and b[r2][c2 + d] == 0:
                    # pivot at (r1,c1)
                    nxt1 = norm(r1, c1, r1, c1 + d)
                    if nxt1 not in visited:
                        visited.add(nxt1)
                        q.append((nxt1, t + 1))
                    # pivot at (r2,c2)
                    nxt2 = norm(r2, c2, r2, c2 + d)
                    if nxt2 not in visited:
                        visited.add(nxt2)
                        q.append((nxt2, t + 1))

    return -1  # unreachable by constraints (safety)