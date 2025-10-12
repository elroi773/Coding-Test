import heapq

def solution(n, k, enemy):
    heap = []  # 최대 힙처럼 쓸 리스트 (음수로 저장)
    
    for i, e in enumerate(enemy):
        heapq.heappush(heap, -e)  # 음수로 저장해서 최대 힙 효과
        n -= e  # 병사로 막기 시도
        
        # 병사가 부족할 경우
        if n < 0:
            if k > 0:  # 무적권이 남아있다면
                largest = -heapq.heappop(heap)  # 가장 큰 적 수를 무적권으로 막기
                n += largest  # 병사 수 복원
                k -= 1
            else:
                return i  # i번째 라운드(0-index)이므로 i가 막은 수
        
    return len(enemy)  # 모든 라운드 막을 수 있으면 전체 길이 반환
