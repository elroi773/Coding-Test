def solution(phone_number):
    length = len(phone_number)
    hidden = '*' * (length - 4)    # 앞부분 *로 가리기
    visible = phone_number[-4:]    # 마지막 4자리
    answer = hidden + visible
    return answer
