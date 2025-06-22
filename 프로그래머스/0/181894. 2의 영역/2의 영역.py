def solution(arr):
    # 2가 배열에 없으면 [-1] 반환
    if 2 not in arr:
        return [-1]

    # 2가 처음 나오는 인덱스와 마지막 인덱스 찾기
    first = arr.index(2)
    last = len(arr) - 1 - arr[::-1].index(2)

    # 부분 배열 잘라서 반환
    return arr[first:last+1]
