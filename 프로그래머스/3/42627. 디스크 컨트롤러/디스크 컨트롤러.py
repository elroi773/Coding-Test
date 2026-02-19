import heapq

def solution(jobs):
    n = len(jobs)
    if n == 0:
        return 0

    # (req, dur, id)
    arr = sorted((s, l, i) for i, (s, l) in enumerate(jobs))

    # 우선순위: dur -> req -> id
    pq = []
    time = 0
    idx = 0
    done = 0
    total = 0

    while done < n:
        # 현재 시각까지 들어온 작업을 큐에 넣기
        while idx < n and arr[idx][0] <= time:
            s, l, i = arr[idx]
            heapq.heappush(pq, (l, s, i))
            idx += 1

        if not pq:
            # 대기 큐가 비면 다음 요청 시각으로 점프
            time = max(time, arr[idx][0])
            continue

        l, s, i = heapq.heappop(pq)
        time += l
        total += (time - s)
        done += 1

    return total // n