def solution(s):
    transform_cnt = 0  # 이진 변환 횟수
    removed_zero_cnt = 0  # 제거된 0의 총 개수

    while s != "1":
        # 현재 문자열에서 0 개수 세기
        zeros = s.count('0')
        removed_zero_cnt += zeros

        # 0 제거
        s = s.replace('0', '')

        # 남은 길이를 2진수 문자열로 변환
        length = len(s)
        s = bin(length)[2:]

        # 변환 횟수 증가
        transform_cnt += 1

    return [transform_cnt, removed_zero_cnt]