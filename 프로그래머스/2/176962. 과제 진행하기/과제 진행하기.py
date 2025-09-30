def solution(plans):
    def to_minutes(time: str) -> int:
        h, m = map(int, time.split(":"))
        return h * 60 + m

    # plans를 숫자형으로 변환 후 시작시간 기준 정렬
    tasks = [(name, to_minutes(start), int(playtime)) for name, start, playtime in plans]
    tasks.sort(key=lambda x: x[1])

    answer = []
    stack = []

    for i in range(len(tasks)):
        name, start, playtime = tasks[i]
        next_start = tasks[i + 1][1] if i < len(tasks) - 1 else float("inf")

        if start + playtime <= next_start:
            # 현재 과제를 끝낼 수 있음
            answer.append(name)
            free_time = next_start - (start + playtime)

            # 멈춘 과제 이어서 처리
            while free_time > 0 and stack:
                prev_name, prev_time = stack.pop()
                if prev_time <= free_time:
                    free_time -= prev_time
                    answer.append(prev_name)
                else:
                    stack.append((prev_name, prev_time - free_time))
                    break
        else:
            # 다 못 끝내고 멈춤
            remain_time = playtime - (next_start - start)
            stack.append((name, remain_time))

    # 스택에 남은 과제 처리
    while stack:
        prev_name, _ = stack.pop()
        answer.append(prev_name)

    return answer
