def solution(info, edges):
    n = len(info)
    children = [[] for _ in range(n)]
    for p, c in edges:
        children[p].append(c)

    # 각 노드가 양(0)인지 늑대(1)인지 비트로 미리
    wolf_mask = 0
    for i, v in enumerate(info):
        if v == 1:
            wolf_mask |= (1 << i)

    def popcount(x: int) -> int:
        # Python 3.8+면 x.bit_count() 사용 가능
        return x.bit_count() if hasattr(int, "bit_count") else bin(x).count("1")

    # mask 상태에서 방문할 수 있는 "다음 후보 노드 집합" 구하기
    def next_candidates(mask: int) -> int:
        cand = 0
        # 방문한 노드들의 자식을 후보에 추가
        for i in range(n):
            if mask & (1 << i):
                for ch in children[i]:
                    cand |= (1 << ch)
        # 이미 방문한 노드는 제외
        cand &= ~mask
        return cand

    best = 0
    seen = set()

    def dfs(mask: int):
        nonlocal best

        if mask in seen:
            return
        seen.add(mask)

        wolves = popcount(mask & wolf_mask)
        sheep = popcount(mask) - wolves

        # 조건 위반이면 진행 불가
        if wolves >= sheep:
            return

        if sheep > best:
            best = sheep

        cand = next_candidates(mask)
        # 후보 노드들을 하나씩 추가해가며 탐색
        while cand:
            nxt = cand & -cand
            cand -= nxt
            dfs(mask | nxt)

    # 루트(0)부터 시작
    dfs(1 << 0)
    return best