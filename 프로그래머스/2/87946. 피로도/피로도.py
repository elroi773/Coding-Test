from itertools import permutations

def solution(k, dungeons):
    max_count = 0  # 탐험 가능한 최대 던전 수
    
    # 모든 던전 순서 경우의 수 확인
    for order in permutations(dungeons, len(dungeons)):
        fatigue = k
        count = 0
        
        for required, cost in order:
            # 최소 필요 피로도보다 현재 피로도가 많으면 탐험 가능
            if fatigue >= required:
                fatigue -= cost
                count += 1
            else:
                break  # 더 이상 못 들어가면 중단
        
        max_count = max(max_count, count)  # 최대값 갱신
    
    return max_count
