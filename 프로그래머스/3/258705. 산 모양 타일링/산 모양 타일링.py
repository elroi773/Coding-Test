def solution(n, tops):
    MOD = 10007

    # v1(맨 왼쪽 삼각형)은 왼쪽과 붙을 수 없으므로 단독만 가능
    free, occ = 1, 0

    # 경로 정점은 총 2n+1개: v1 ... v(2n+1)
    # 짝수 번째 정점(v2, v4, ...)이 윗변과 맞닿는 삼각형(D_i)이고, 여기에 tops[i-1] 가지가 달릴 수 있음
    for j in range(2, 2 * n + 2):  # 2 .. 2n+1
        has_top = tops[j // 2 - 1] if j % 2 == 0 else 0  # 0 or 1

        new_free = (free + occ) % MOD              # 단독 배치(왼쪽과 안 붙음)
        new_occ = free % MOD                       # 왼쪽과 붙여 마름모(경로 간선)

        if has_top:
            new_occ = (new_occ + (free + occ)) % MOD  # 위 가지와 붙여 마름모

        free, occ = new_free, new_occ

    return (free + occ) % MOD