import heapq

def solution(book_time):
    # "HH:MM" → 분으로 변환하는 함수
    def to_minutes(t):
        h, m = map(int, t.split(":"))
        return h * 60 + m

    # 1️⃣ 각 예약을 [시작, 종료+10] 형태로 변환
    times = []
    for start, end in book_time:
        times.append([to_minutes(start), to_minutes(end) + 10])

    # 2️⃣ 시작 시각 기준 정렬
    times.sort(key=lambda x: x[0])

    # 3️⃣ 최소 힙 (방의 청소 완료 시각)
    rooms = []

    for start, end in times:
        # 가장 빨리 끝나는 방이 현재 예약 시작 이전에 끝났으면 재사용
        if rooms and rooms[0] <= start:
            heapq.heappop(rooms)
        heapq.heappush(rooms, end)

    # 4️⃣ 힙의 크기 = 필요한 최소 객실 수
    return len(rooms)
