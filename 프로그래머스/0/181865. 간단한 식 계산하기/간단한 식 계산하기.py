def solution(binomial):
    a, op, b = binomial.split()
    a, b = int(a), int(b)

    if op == '+':
        return a + b
    elif op == '-':
        return a - b
    elif op == '*':
        return a * b
