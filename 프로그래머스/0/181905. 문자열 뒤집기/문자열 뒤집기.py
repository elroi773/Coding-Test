def solution(my_string, s, e):
    # 인덱스 s부터 e까지의 부분 문자열을 뒤집음
    reversed_part = my_string[s:e+1][::-1]
    # 뒤집힌 부분을 원래 문자열에서 해당 부분을 교체한 결과
    answer = my_string[:s] + reversed_part + my_string[e+1:]
    return answer
