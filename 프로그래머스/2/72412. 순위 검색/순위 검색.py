from collections import defaultdict
from bisect import bisect_left

def solution(info, query):
    db = defaultdict(list)

    # 1. info 전처리: 각 지원자에 대해 2^4 = 16가지 조합의 key에 점수 추가
    for line in info:
        lang, job, career, food, score_str = line.split()
        score = int(score_str)
        attrs = [lang, job, career, food]

        # 0 ~ 15 (2^4 가지 조합)
        for mask in range(16):
            key_parts = []
            for i in range(4):
                if mask & (1 << i):
                    key_parts.append('-')     # 해당 조건 무시
                else:
                    key_parts.append(attrs[i])  # 원래 값 사용
            key = ' '.join(key_parts)
            db[key].append(score)

    # 2. 각 key별 점수 리스트 정렬
    for key in db:
        db[key].sort()

    answer = []

    # 3. query 처리
    for q in query:
        # "java and backend and junior and pizza 100"
        # -> "java backend junior pizza 100"
        q = q.replace(" and ", " ")
        parts = q.split()

        key = ' '.join(parts[:4])
        target = int(parts[4])

        scores = db.get(key, [])
        # target 이상인 사람 수 = 전체 길이 - lower_bound 위치
        idx = bisect_left(scores, target)
        answer.append(len(scores) - idx)

    return answer