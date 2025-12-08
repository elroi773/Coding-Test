def solution(n):
    # 삼각형 초기화
    triangle = [[0] * (i+1) for i in range(n)]
    
    x, y = -1, 0  # 시작 위치 (항상 아래로 한 칸 이동한 상태에서 시작하기 위해 x = -1)
    num = 1       # 넣을 숫자

    for step in range(n):
        for _ in range(step, n):
            if step % 3 == 0:          # 아래로 이동
                x += 1
            elif step % 3 == 1:        # 오른쪽으로 이동
                y += 1
            else:                      # 왼쪽 위 대각선으로 이동
                x -= 1
                y -= 1

            triangle[x][y] = num
            num += 1

    # 1차원 배열로 펼치기
    answer = []
    for row in triangle:
        answer.extend(row)

    return answer
