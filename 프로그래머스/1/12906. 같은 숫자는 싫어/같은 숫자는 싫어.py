def solution(arr):
    answer = []
    # 첫 번째 원소는 무조건 추가
    answer.append(arr[0])

    for i in range(1, len(arr)):
        if arr[i] != arr[i-1]:  # 직전 원소와 다르면 추가
            answer.append(arr[i])
    return answer
