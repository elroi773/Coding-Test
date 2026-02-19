import heapq

def solution(operations):
    min_h = []  # (value, id)
    max_h = []  # (-value, id)
    alive = [False] * len(operations)

    next_id = 0
    valid = 0

    def clean_min():
        while min_h and not alive[min_h[0][1]]:
            heapq.heappop(min_h)

    def clean_max():
        while max_h and not alive[max_h[0][1]]:
            heapq.heappop(max_h)

    for op in operations:
        if op[0] == 'I':
            x = int(op[2:])
            idx = next_id
            next_id += 1
            alive[idx] = True
            heapq.heappush(min_h, (x, idx))
            heapq.heappush(max_h, (-x, idx))
            valid += 1
        else:  # 'D'
            if valid == 0:
                continue

            if op[2] == '1':  # delete max
                clean_max()
                if max_h:
                    _, idx = heapq.heappop(max_h)
                    alive[idx] = False
                    valid -= 1
            else:  # delete min (D -1)
                clean_min()
                if min_h:
                    _, idx = heapq.heappop(min_h)
                    alive[idx] = False
                    valid -= 1

    if valid == 0:
        return [0, 0]

    clean_min()
    clean_max()

    return [-max_h[0][0], min_h[0][0]]