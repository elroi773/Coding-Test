import heapq

def solution(scoville, K):
    heapq.heapify(scoville)  # 리스트를 최소 힙으로 변환
    count = 0

    while scoville[0] < K:
        if len(scoville) < 2:
            return -1

        first = heapq.heappop(scoville)
        second = heapq.heappop(scoville)

        mixed = first + second * 2
        heapq.heappush(scoville, mixed)

        count += 1

    return count
