def solution(n, info):
    # info: 어피치의 화살 배치 (10점 ~ 0점)
    best_diff = 0          # 최대 점수 차이
    best = [-1]            # 최적 배치 (없으면 [-1])

    ryan = [0] * 11        # 현재 탐색 중인 라이언 배치

    def dfs(idx, arrows_left):
        nonlocal best_diff, best

        # 마지막 점수(0점, idx == 10)에 도달하면
        if idx == 10:
            # 남은 화살 전부 0점(인덱스 10)에 넣기
            ryan[10] = arrows_left

            # 점수 계산
            r_score = 0
            a_score = 0
            for i in range(11):
                if info[i] == 0 and ryan[i] == 0:
                    continue  # 둘 다 안 맞춤
                if ryan[i] > info[i]:
                    r_score += 10 - i
                else:
                    a_score += 10 - i

            diff = r_score - a_score

            # 라이언이 이기지 못하면 버린다.
            if diff <= 0:
                # 원상 복구
                ryan[10] = 0
                return

            # 더 큰 점수 차이면 무조건 채택
            if diff > best_diff or best == [-1]:
                best_diff = diff
                best = ryan[:]  # 복사
            # 점수 차이가 같으면, 낮은 점수 많이 맞힌 쪽 선택
            elif diff == best_diff:
                # 인덱스 10(0점)부터 0(10점)까지 거꾸로 비교
                for i in range(10, -1, -1):
                    if ryan[i] > best[i]:
                        best = ryan[:]
                        break
                    elif ryan[i] < best[i]:
                        break

            # 원상 복구
            ryan[10] = 0
            return

        # 1) 현재 점수(10 - idx점)를 이기기 위해 쏘는 경우
        need = info[idx] + 1  # 어피치보다 1발 더 쏴야 이김
        if need <= arrows_left:
            ryan[idx] = need
            dfs(idx + 1, arrows_left - need)
            ryan[idx] = 0  # 백트래킹

        # 2) 현재 점수를 포기하는 경우 (0발)
        dfs(idx + 1, arrows_left)

    # DFS 시작 (10점에서부터)
    dfs(0, n)

    # 이길 수 있는 배치가 없으면 [-1]
    if best == [-1]:
        return [-1]
    return best