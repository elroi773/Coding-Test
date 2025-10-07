def solution(m, n, startX, startY, balls):
    answer = []
    
    for a, b in balls:
        min_dist = float('inf')

        # 1️⃣ 위쪽 벽 반사
        if not (startX == a and startY < b):  # 같은 세로선 위에서 위로 직접 맞는 경우 제외
            y = 2 * n - b
            dist = (startX - a) ** 2 + (startY - y) ** 2
            min_dist = min(min_dist, dist)

        # 2️⃣ 아래쪽 벽 반사
        if not (startX == a and startY > b):  # 아래로 직접 맞는 경우 제외
            y = -b
            dist = (startX - a) ** 2 + (startY - y) ** 2
            min_dist = min(min_dist, dist)

        # 3️⃣ 왼쪽 벽 반사
        if not (startY == b and startX > a):  # 왼쪽으로 직접 맞는 경우 제외
            x = -a
            dist = (startX - x) ** 2 + (startY - b) ** 2
            min_dist = min(min_dist, dist)

        # 4️⃣ 오른쪽 벽 반사
        if not (startY == b and startX < a):  # 오른쪽으로 직접 맞는 경우 제외
            x = 2 * m - a
            dist = (startX - x) ** 2 + (startY - b) ** 2
            min_dist = min(min_dist, dist)

        answer.append(min_dist)

    return answer
