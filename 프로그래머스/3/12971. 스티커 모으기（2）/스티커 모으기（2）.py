def solve_linear(arr, l, r):  # inclusive [l..r]
    prev2, prev1 = 0, 0  # dp[i-2], dp[i-1]
    for i in range(l, r + 1):
        cur = max(prev1, prev2 + arr[i])
        prev2, prev1 = prev1, cur
    return prev1

def solution(sticker):
    n = len(sticker)
    if n == 1:
        return sticker[0]
    if n == 2:
        return max(sticker[0], sticker[1])

    # Case A: take first -> cannot take last
    case_a = solve_linear(sticker, 0, n - 2)

    # Case B: skip first -> can take last
    case_b = solve_linear(sticker, 1, n - 1)

    return max(case_a, case_b)