from collections import deque

def solution(queue1, queue2):
    q1 = deque(queue1)
    q2 = deque(queue2)
    
    sum1 = sum(q1)
    sum2 = sum(q2)
    total = sum1 + sum2
    
    if total % 2 != 0:
        return -1
    
    target = total // 2
    cnt = 0
    max_ops = len(q1) * 3  # 최대 시도 횟수, 안전 마진
    
    while cnt <= max_ops:
        if sum1 == target:
            return cnt
        if sum1 > target:
            # q1에서 q2로 이동
            if not q1:
                return -1
            x = q1.popleft()
            sum1 -= x
            sum2 += x
            q2.append(x)
        else:
            # q2에서 q1로 이동
            if not q2:
                return -1
            x = q2.popleft()
            sum2 -= x
            sum1 += x
            q1.append(x)
        cnt += 1
    
    return -1
