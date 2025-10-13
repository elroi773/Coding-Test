from collections import Counter

def solution(k, tangerine):
    counts = Counter(tangerine)          # 귤 크기별 개수 세기
    sorted_counts = sorted(counts.values(), reverse=True)  # 개수 내림차순 정렬
    
    answer = 0
    total = 0
    
    for count in sorted_counts:
        total += count      # 현재 크기 귤 더하기
        answer += 1         # 사용한 크기 종류 +1
        if total >= k:      # k개 이상 채우면 종료
            break
            
    return answer
