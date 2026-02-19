def solution(n, costs):
    # Kruskal + Union-Find(DSU)

    parent = list(range(n))
    rank = [0] * n

    def find(x):
        while parent[x] != x:
            parent[x] = parent[parent[x]]  # path compression (halving)
            x = parent[x]
        return x

    def union(a, b):
        ra, rb = find(a), find(b)
        if ra == rb:
            return False
        if rank[ra] < rank[rb]:
            ra, rb = rb, ra
        parent[rb] = ra
        if rank[ra] == rank[rb]:
            rank[ra] += 1
        return True

    costs.sort(key=lambda x: x[2])

    answer = 0
    picked = 0
    for u, v, w in costs:
        if union(u, v):
            answer += w
            picked += 1
            if picked == n - 1:
                break

    return answer