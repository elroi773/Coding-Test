def solution(brown, yellow):
    total = brown + yellow
    for h in range(1, total + 1):
        if total % h == 0:
            w = total // h
            if (w - 2) * (h - 2) == yellow and w >= h:
                return [w, h]
