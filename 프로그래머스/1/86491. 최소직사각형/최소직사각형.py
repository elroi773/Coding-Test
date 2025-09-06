def solution(sizes):
    max_w, max_h = 0, 0

    for w, h in sizes:
        w, h = max(w, h), min(w, h)  # 큰 쪽을 가로, 작은 쪽을 세로
        max_w = max(max_w, w)
        max_h = max(max_h, h)

    return max_w * max_h

