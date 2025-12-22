def solution(n):
    answer = 0
    
    cols = [False] * n            # 열 체크
    diag1 = [False] * (2 * n)     # ↘ 대각선 (row - col + n)
    diag2 = [False] * (2 * n)     # ↗ 대각선 (row + col)

    def backtrack(row):
        nonlocal answer
        
        # 모든 행에 퀸을 배치한 경우
        if row == n:
            answer += 1
            return
        
        for col in range(n):
            if cols[col] or diag1[row - col + n] or diag2[row + col]:
                continue
            
            # 퀸 배치
            cols[col] = diag1[row - col + n] = diag2[row + col] = True
            
            backtrack(row + 1)
            
            # 퀸 제거 (백트래킹)
            cols[col] = diag1[row - col + n] = diag2[row + col] = False

    backtrack(0)
    return answer
