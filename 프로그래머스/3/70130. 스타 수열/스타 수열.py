# solution.py

def solution(a):
    n = len(a)
    if n < 2:
        return 0

    # edges[v] = [i, ...] where i is an index such that (a[i], a[i+1]) is a valid adjacent pair
    # containing v (and a[i] != a[i+1]). Lists are naturally sorted by construction.
    edges = [None] * n
    arr = a

    for i in range(n - 1):
        x = arr[i]
        y = arr[i + 1]
        if x == y:
            continue

        lst = edges[x]
        if lst is None:
            edges[x] = [i]
        else:
            lst.append(i)

        lst = edges[y]
        if lst is None:
            edges[y] = [i]
        else:
            lst.append(i)

    best = 0

    # For each candidate common element k, pick the maximum number of non-overlapping edges
    # (i, i+1) that include k. Each chosen edge contributes 2 elements to the star subsequence.
    for lst in edges:
        if lst is None:
            continue

        # Upper bound: can use at most len(lst) pairs for this k
        if len(lst) * 2 <= best:
            continue

        cnt = 0
        last = -2  # last chosen edge index
        for idx in lst:
            # edges idx and last conflict if they are the same or adjacent (share a vertex),
            # so we need idx >= last + 2
            if idx >= last + 2:
                cnt += 1
                last = idx

        cand = cnt * 2
        if cand > best:
            best = cand

    return best