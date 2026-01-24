def solution(n, k, cmd):
    prev = [i - 1 for i in range(n)]
    nxt  = [i + 1 for i in range(n)]
    nxt[n - 1] = -1

    removed = [False] * n
    stack = []  # (idx, prevIdx, nextIdx)

    cur = k

    for s in cmd:
        op = s[0]
        if op == 'U':
            x = int(s[2:])
            for _ in range(x):
                cur = prev[cur]
        elif op == 'D':
            x = int(s[2:])
            for _ in range(x):
                cur = nxt[cur]
        elif op == 'C':
            removed[cur] = True
            p, nx = prev[cur], nxt[cur]
            stack.append((cur, p, nx))

            if p != -1:
                nxt[p] = nx
            if nx != -1:
                prev[nx] = p

            cur = nx if nx != -1 else p
        else:  # 'Z'
            idx, p, nx = stack.pop()
            removed[idx] = False

            if p != -1:
                nxt[p] = idx
            if nx != -1:
                prev[nx] = idx
            prev[idx] = p
            nxt[idx] = nx

    return ''.join('X' if removed[i] else 'O' for i in range(n))