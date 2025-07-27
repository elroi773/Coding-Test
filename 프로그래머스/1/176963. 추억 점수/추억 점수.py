def solution(name, yearning, photo):
    # 1. 이름과 점수를 매핑
    score_map = {n: y for n, y in zip(name, yearning)}
    
    answer = []
    
    # 2. 사진별 인물 점수 합산
    for people in photo:
        total = 0
        for person in people:
            total += score_map.get(person, 0)  # 없으면 0점
        answer.append(total)
    
    return answer
