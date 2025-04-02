def solution(arr1, arr2):
    if len(arr1) > len(arr2):  # 배열 길이 비교
        return 1
    elif len(arr1) < len(arr2):
        return -1
    else:  # 길이가 같을 경우 합 비교
        return 1 if sum(arr1) > sum(arr2) else -1 if sum(arr1) < sum(arr2) else 0
