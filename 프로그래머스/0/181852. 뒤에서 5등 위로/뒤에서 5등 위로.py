def solution(num_list):
    num_list.sort()  # 오름차순 정렬
    return num_list[5:]  # 앞의 5개 원소 제거 후 반환
