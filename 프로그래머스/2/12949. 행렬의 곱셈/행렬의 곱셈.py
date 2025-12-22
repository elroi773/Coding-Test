def solution(arr1, arr2):
    answer = []
    
    for i in range(len(arr1)):          # arr1의 행
        row = []
        for j in range(len(arr2[0])):   # arr2의 열
            total = 0
            for k in range(len(arr2)):  # 공통 차원
                total += arr1[i][k] * arr2[k][j]
            row.append(total)
        answer.append(row)
    
    return answer
