def solution(park, routes):
    h, w = len(park), len(park[0])

    # 시작 좌표 찾기
    x, y = 0, 0
    for i in range(h):
        for j in range(w):
            if park[i][j] == "S":
                x, y = i, j

    # 방향 정의
    dirs = {
        "N": (-1, 0),
        "S": (1, 0),
        "W": (0, -1),
        "E": (0, 1)
    }

    # 명령 실행
    for route in routes:
        d, n = route.split()
        n = int(n)
        dx, dy = dirs[d]

        nx, ny = x, y
        can_move = True

        for step in range(1, n+1):
            tx = x + dx * step
            ty = y + dy * step
            if not (0 <= tx < h and 0 <= ty < w) or park[tx][ty] == "X":
                can_move = False
                break
            nx, ny = tx, ty

        if can_move:
            x, y = nx, ny

    return [x, y]
