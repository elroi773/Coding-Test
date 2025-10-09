from collections import deque

def solution(x, y, n):
    queue = deque([(x, 0)])
    visited = set([x])
    
    while queue:
        cur, cnt = queue.popleft()
        
        if cur == y:
            return cnt
        
        for next_val in (cur + n, cur * 2, cur * 3):
            if next_val <= y and next_val not in visited:
                visited.add(next_val)
                queue.append((next_val, cnt + 1))
    
    return -1
