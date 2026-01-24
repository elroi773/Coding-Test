def solution(n, k, cmd):
    # doubly linked list by index
    prev = [i - 1 for i in range(n)]
    nxt  = [i + 1 for i in range(n)]
    nxt[n - 1] = -1

    removed = [False] * n
    stack = []  # (idx, prev_idx, next_idx)

    cur = k

    for s in cmd:
        if s[0] == 'U':
            x = int(s[2:])
            for _ in range(x):
                cur = prev[cur]
        elif s[0] == 'D':
            x = int(s[2:])
            for _ in range(x):
                cur = nxt[cur]
        elif s[0] == 'C':
            removed[cur] = True
            p, n_ = prev[cur], nxt[cur]
            stack.append((cur, p, n_))

            # unlink cur
            if p != -1:
                nxt[p] = n_
            if n_ != -1:
                prev[n_] = p

            # move selection
            cur = n_ if n_ != -1 else p
        else:  # 'Z'
            idx, p, n_ = stack.pop()
            removed[idx] = False

            # relink idx
            if p != -1:
                nxt[p] = idx
            if n_ != -1:
                prev[n_] = idx
            prev[idx] = p
            nxt[idx] = n_

    return ''.join('X' if removed[i] else 'O' for i in range(n))