def solution(start_num, end_num):
    answer = []
    size = start_num - end_num +1;
    answer = [0] * size
    for i in range(size):
        answer[i] = start_num - i
    return answer