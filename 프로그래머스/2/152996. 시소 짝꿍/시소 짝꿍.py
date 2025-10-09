def solution(weights):
    from collections import Counter
    
    answer = 0
    ratios = [1, 1.5, 2, 4/3]  # 가능한 비율들
    count = Counter(weights)   # 각 몸무게 등장 횟수
    weights = sorted(count.keys())
    
    for w in weights:
        # 같은 몸무게끼리 짝꿍이 되는 경우 (조합 nC2)
        answer += count[w] * (count[w] - 1) // 2
        
        # 다른 비율로 짝꿍 되는 경우
        for r in ratios[1:]:  # 1 제외 (이미 처리함)
            target = w * r
            if target in count:
                answer += count[w] * count[target]
    
    return answer
