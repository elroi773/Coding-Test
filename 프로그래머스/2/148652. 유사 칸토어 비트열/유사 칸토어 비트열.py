def solution(n, l, r):
    def count_ones(n):
        if n == 0:
            return 1
        return count_ones(n-1) * 4

    def dfs(n, start, end, l, r):
        # 범위 겹치지 않으면 0
        if r < start or l > end:
            return 0
        # n == 0이면 단일 1 반환
        if n == 0:
            return 1
        length = (end - start + 1) // 5
        res = 0
        # 5개 블록
        for i in range(5):
            block_start = start + i * length
            block_end = block_start + length - 1
            if i == 2:  # 가운데 블록: 0만 있음
                continue
            res += dfs(n-1, block_start, block_end, l, r)
        return res

    total_length = 5 ** n
    return dfs(n, 1, total_length, l, r)
