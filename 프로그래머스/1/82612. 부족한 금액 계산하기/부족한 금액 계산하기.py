def solution(price, money, count):
    total = price * count * (count + 1) // 2
    needed = total - money
    return needed if needed > 0 else 0
