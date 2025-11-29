def solution(numbers, target):
    answer = 0

    def dfs(index, current_sum):
        nonlocal answer

        # 모든 숫자를 사용한 경우
        if index == len(numbers):
            if current_sum == target:
                answer += 1
            return

        # 현재 숫자를 더하는 경우
        dfs(index + 1, current_sum + numbers[index])
        # 현재 숫자를 빼는 경우
        dfs(index + 1, current_sum - numbers[index])

    dfs(0, 0)
    return answer

