def solution(numbers):
    answer = []
    for x in numbers:
        if x % 2 == 0:
            # 짝수는 그냥 +1
            answer.append(x + 1)
        else:
            # 홀수일 때
            # 오른쪽에서 첫 0 찾기
            bin_x = '0' + bin(x)[2:]  # 맨 앞에 0 추가
            i = len(bin_x) - 1
            while bin_x[i] != '0':
                i -= 1
            # 그 위치를 1로 바꾸고 바로 오른쪽 1비트만 0으로
            new_bin = list(bin_x)
            new_bin[i] = '1'
            new_bin[i+1] = '0'
            answer.append(int(''.join(new_bin), 2))
    return answer

# 테스트
print(solution([2,7]))  # [3, 11]

