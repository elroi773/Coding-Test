def solution(expression):
    import re
    from itertools import permutations
    
    # 숫자와 연산자 분리
    nums = list(map(int, re.findall(r'\d+', expression)))
    ops = re.findall(r'[\+\-\*]', expression)

    # 등장한 연산자 종류 추출
    unique_ops = list(set(ops))
    
    max_result = 0

    # 연산 함수 정의
    def calc(a, b, op):
        if op == '+':
            return a + b
        elif op == '-':
            return a - b
        return a * b  # '*'

    # 가능한 모든 우선순위 순열 고려
    for priority in permutations(unique_ops):
        tmp_nums = nums[:]
        tmp_ops = ops[:]

        # 현재 우선순위의 연산자부터 차례대로 계산
        for op in priority:
            new_nums = [tmp_nums[0]]
            new_ops = []

            for i in range(len(tmp_ops)):
                if tmp_ops[i] == op:
                    # 바로 이전 숫자와 계산하여 마지막 숫자 교체
                    new_nums[-1] = calc(new_nums[-1], tmp_nums[i+1], op)
                else:
                    new_nums.append(tmp_nums[i+1])
                    new_ops.append(tmp_ops[i])

            tmp_nums, tmp_ops = new_nums, new_ops

        # 마지막 값의 절댓값으로 최댓값 갱신
        max_result = max(max_result, abs(tmp_nums[0]))

    return max_result
