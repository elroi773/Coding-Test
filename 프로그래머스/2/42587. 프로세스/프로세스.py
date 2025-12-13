from collections import deque

def solution(priorities, location):
    queue = deque([(p, i) for i, p in enumerate(priorities)])
    order = 0

    while queue:
        priority, idx = queue.popleft()

        # 더 높은 우선순위가 있는지 확인
        if any(priority < q[0] for q in queue):
            queue.append((priority, idx))
        else:
            order += 1
            if idx == location:
                return order
