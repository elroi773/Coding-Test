def solution(arr):
    answer = [0, 0]  # answer[0] = 0의 개수, answer[1] = 1의 개수

    # 특정 영역이 모두 같은 값인지 확인
    def is_same(x, y, size):
        value = arr[x][y]
        for i in range(x, x + size):
            for j in range(y, y + size):
                if arr[i][j] != value:
                    return False
        return True

    # 쿼드트리 압축 재귀 함수
    def compress(x, y, size):
        if is_same(x, y, size):
            answer[arr[x][y]] += 1
            return

        new_size = size // 2

        compress(x, y, new_size)                  # 좌상
        compress(x, y + new_size, new_size)       # 우상
        compress(x + new_size, y, new_size)       # 좌하
        compress(x + new_size, y + new_size, new_size)  # 우하

    compress(0, 0, len(arr))
    return answer
