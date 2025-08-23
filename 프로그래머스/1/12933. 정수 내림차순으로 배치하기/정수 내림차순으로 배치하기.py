def solution(n):
    # 숫자를 문자열로 바꾼 뒤 내림차순 정렬하고 다시 숫자로 변환
    return int("".join(sorted(str(n), reverse=True)))
