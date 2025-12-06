def solution(n, q, ans):
    m = len(q)          # 시도 횟수
    code = [0] * 5      # 현재 만들고 있는 비밀 코드 후보 (5개 숫자)
    count = 0           # 가능한 비밀 코드 개수

    # code[5]와 row[5] 사이의 공통 원소 개수 (둘 다 오름차순)
    def intersection_count(code, row):
        i = j = cnt = 0
        while i < 5 and j < 5:
            if code[i] == row[j]:
                cnt += 1
                i += 1
                j += 1
            elif code[i] < row[j]:
                i += 1
            else:
                j += 1
        return cnt

    # 현재 code[]가 모든 시도(q, ans)를 만족하는지 확인
    def is_valid():
        for row, expected in zip(q, ans):
            if intersection_count(code, row) != expected:
                return False
        return True

    # DFS로 1~n 중 5개의 조합 생성
    def dfs(idx, start):
        nonlocal count
        if idx == 5:
            if is_valid():
                count += 1
            return

        # 남은 자리를 채울 수 있는 최대 숫자까지만 반복
        max_start = n - (5 - idx) + 1
        for num in range(start, max_start + 1):
            code[idx] = num
            dfs(idx + 1, num + 1)

    dfs(0, 1)
    return count