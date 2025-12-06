from collections import defaultdict
from bisect import bisect_left

def solution(info, query):
    db = defaultdict(list)
    
    # 1. info 전처리: 모든 조합 key에 점수 넣기
    for line in info:
        lang, job, career, food, score = line.split()
        score = int(score)
        attrs = [lang, job, career, food]
        
        # 2^4 = 16가지 조합
        for mask in range(16):
            key_parts = []
            for i in range(4):
                if mask & (1 << i):
                    key_parts.append('-')
                else:
                    key_parts.append(attrs[i])
            key = ' '.join(key_parts)
            db[key].append(score)
    
    # 2. 각 key별 점수 리스트 정렬
    for key in db:
        db[key].sort()
    
    # 3. query 처리
    answer = []
    for q in query:
        parts = q.split()
        lang = parts[0]
        job = parts[2]
        career = parts[4]
        food = parts[6]
        score = int(parts[7])
        
        key = ' '.join([lang, job, career, food])
        scores = db.get(key, [])
        
        # score 이상인 사람 수 = 전체 길이 - lower_bound 위치
        idx = bisect_left(scores, score)
        answer.append(len(scores) - idx)
    
    return answer