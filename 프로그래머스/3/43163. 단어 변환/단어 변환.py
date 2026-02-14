from collections import deque

def _diff_one(a: str, b: str) -> bool:
    """두 단어가 정확히 한 글자만 다른지"""
    diff = 0
    for ca, cb in zip(a, b):
        if ca != cb:
            diff += 1
            if diff > 1:
                return False
    return diff == 1

def solution(begin, target, words):
    if target not in words:
        return 0

    n = len(words)
    visited = [False] * n
    q = deque()
    q.append((begin, 0))  # (현재 단어, 변환 횟수)

    while q:
        cur, steps = q.popleft()
        if cur == target:
            return steps

        for i in range(n):
            if not visited[i] and _diff_one(cur, words[i]):
                visited[i] = True
                q.append((words[i], steps + 1))

    return 0