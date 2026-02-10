def solution(n, build_frame):
    S = set()  # (x, y, a)

    def can_place(x, y, a):
        if a == 0:  # 기둥
            return (
                y == 0 or
                (x, y - 1, 0) in S or
                (x - 1, y, 1) in S or
                (x, y, 1) in S
            )
        else:  # 보
            return (
                (x, y - 1, 0) in S or
                (x + 1, y - 1, 0) in S or
                ((x - 1, y, 1) in S and (x + 1, y, 1) in S)
            )

    def valid_all():
        for x, y, a in S:
            if not can_place(x, y, a):
                return False
        return True

    for x, y, a, b in build_frame:
        if b == 1:  # 설치
            S.add((x, y, a))
            if not valid_all():
                S.remove((x, y, a))
        else:  # 삭제
            S.remove((x, y, a))
            if not valid_all():
                S.add((x, y, a))

    return sorted(map(list, S), key=lambda v: (v[0], v[1], v[2]))