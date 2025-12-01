def solution(rows, columns, queries):
    # 행렬 초기화
    matrix = [[i * columns + j + 1 for j in range(columns)] for i in range(rows)]
    answer = []

    for x1, y1, x2, y2 in queries:
        # 0-index 변환
        x1, y1, x2, y2 = x1-1, y1-1, x2-1, y2-1
        
        # 테두리 숫자 저장
        prev = matrix[x1][y1]
        min_val = prev

        # 위쪽 행 이동 (왼쪽 → 오른쪽)
        for j in range(y1+1, y2+1):
            matrix[x1][j], prev = prev, matrix[x1][j]
            min_val = min(min_val, prev)
        # 오른쪽 열 이동 (위 → 아래)
        for i in range(x1+1, x2+1):
            matrix[i][y2], prev = prev, matrix[i][y2]
            min_val = min(min_val, prev)
        # 아래쪽 행 이동 (오른쪽 → 왼쪽)
        for j in range(y2-1, y1-1, -1):
            matrix[x2][j], prev = prev, matrix[x2][j]
            min_val = min(min_val, prev)
        # 왼쪽 열 이동 (아래 → 위)
        for i in range(x2-1, x1-1, -1):
            matrix[i][y1], prev = prev, matrix[i][y1]
            min_val = min(min_val, prev)

        answer.append(min_val)

    return answer
