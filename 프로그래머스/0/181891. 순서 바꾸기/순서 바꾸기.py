def solution(num_list, n):
    front = num_list[:n]     # n번째까지의 원소들 (0 ~ n-1)
    back = num_list[n:]      # n번째 이후의 원소들 (n ~ 끝)
    return back + front      # back + front 순서로 이어 붙임
