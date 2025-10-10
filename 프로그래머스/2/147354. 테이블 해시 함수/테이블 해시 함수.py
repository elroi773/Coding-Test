def solution(data, col, row_begin, row_end):
    # 1. 정렬
    data.sort(key=lambda x: (x[col-1], -x[0]))

    # 2. S_i 계산 및 XOR 누적
    answer = 0
    for i in range(row_begin, row_end + 1):
        row = data[i - 1]  # i번째 행 (인덱스는 i-1)
        S_i = sum(value % i for value in row)
        answer ^= S_i  # XOR 연산

    return answer
