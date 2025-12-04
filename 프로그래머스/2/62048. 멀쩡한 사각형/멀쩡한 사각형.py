import math

def solution(w, h):
    g = math.gcd(w, h)
    return w * h - (w + h - g)