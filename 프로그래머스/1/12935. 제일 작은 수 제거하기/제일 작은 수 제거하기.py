def solution(arr):
    if len(arr) == 1:
        return [-1]
    
    min_value = min(arr)  # 최소값 찾기
    arr.remove(min_value)  # 리스트에서 최소값 제거 (첫 번째 값만 제거됨)
    return arr
