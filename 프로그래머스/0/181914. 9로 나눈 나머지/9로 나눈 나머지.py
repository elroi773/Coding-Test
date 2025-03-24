def solution(number: str)-> int:
    return sum(int(digit) for digit in number)%9