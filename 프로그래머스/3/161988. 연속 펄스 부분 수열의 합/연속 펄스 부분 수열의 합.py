def solution(sequence):
    max_sum = 0

    curr1 = 0  # [1, -1, 1, -1 ...]
    curr2 = 0  # [-1, 1, -1, 1 ...]

    for i, num in enumerate(sequence):
        sign1 = 1 if i % 2 == 0 else -1
        sign2 = -sign1

        curr1 = max(num * sign1, curr1 + num * sign1)
        curr2 = max(num * sign2, curr2 + num * sign2)

        max_sum = max(max_sum, curr1, curr2)

    return max_sum
