import re
from itertools import permutations

def solution(expression):
    # 1. 숫자와 연산자 분리
    nums = list(map(int, re.split(r'([\+\-\*])', expression)[::2]))
    ops  = re.findall(r'[\+\-\*]', expression)

    # 등장한 연산자 추출
    unique_ops = list(set(ops))

    max_result = 0

    # 2. 연산자 우선순위 순열 생성
    for priority in permutations(unique_ops):
        result = evaluate(nums, ops, priority)
        max_result = max(max_result, abs(result))

    return max_result


# 우선순위 계산 함수
def evaluate(nums, ops, priority):
    tmp_nums = nums[:]
    tmp_ops = ops[:]

    for op in priority:
        new_nums = [tmp_nums[0]]
        new_ops = []

        for i in range(len(tmp_ops)):
            if tmp_ops[i] == op:
                a = new_nums.pop()
                b = tmp_nums[i + 1]
                new_nums.append(calc(a, b, op))
            else:
                new_nums.append(tmp_nums[i + 1])
                new_ops.append(tmp_ops[i])

        tmp_nums = new_nums
        tmp_ops = new_ops

    return tmp_nums[0]


# 실제 연산 함수
def calc(a, b, op):
    if op == '+':
        return a + b
    elif op == '-':
        return a - b
    else:
        return a * b
