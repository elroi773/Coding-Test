def solution(name):
    answer = 0
    n = len(name)

    # 1. 알파벳 변경 최소 조작
    for ch in name:
        answer += min(ord(ch) - ord('A'), ord('Z') - ord(ch) + 1)

    # 2. 커서 이동 최소 조작
    move = n - 1  # 기본적으로 오른쪽으로 쭉 가기

    for i in range(n):
        next_idx = i + 1

        # 다음 문자부터 연속된 A 찾기
        while next_idx < n and name[next_idx] == 'A':
            next_idx += 1

        # 2가지 전략 비교
        # ① i까지 갔다가 뒤로 돌아서 끝으로 가기
        # ② 처음부터 끝으로 갔다가 i로 다시 돌아오기
        move = min(
            move,
            2 * i + (n - next_idx),
            i + 2 * (n - next_idx)
        )

    return answer + move
