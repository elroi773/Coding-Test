def solution(line):
    points = set()  # 정수 교점 저장
    n = len(line)

    # 1️⃣ 모든 직선 쌍의 교점 구하기
    for i in range(n):
        A, B, E = line[i]
        for j in range(i + 1, n):
            C, D, F = line[j]

            # 두 직선의 결정식
            denom = A * D - B * C
            if denom == 0:
                continue  # 평행 또는 일치
            
            # 교점 (x, y)
            x = (B * F - E * D) / denom
            y = (E * C - A * F) / denom

            # 정수 좌표만 저장
            if x.is_integer() and y.is_integer():
                points.add((int(x), int(y)))

    # 2️⃣ 별을 찍을 최소 사각형 범위 찾기
    xs = [x for x, _ in points]
    ys = [y for _, y in points]
    min_x, max_x = min(xs), max(xs)
    min_y, max_y = min(ys), max(ys)

    width = max_x - min_x + 1
    height = max_y - min_y + 1

    # 3️⃣ .으로 채운 격자 생성
    grid = [['.'] * width for _ in range(height)]

    # 4️⃣ 별 표시 (*)
    for x, y in points:
        grid[max_y - y][x - min_x] = '*'

    # 5️⃣ 문자열로 변환
    return [''.join(row) for row in grid]
