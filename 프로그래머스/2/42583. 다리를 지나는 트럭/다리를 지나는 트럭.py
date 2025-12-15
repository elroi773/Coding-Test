from collections import deque

def solution(bridge_length, weight, truck_weights):
    bridge = deque([0] * bridge_length)
    trucks = deque(truck_weights)
    time = 0
    current_weight = 0

    while bridge:
        time += 1

        # 트럭이 다리를 빠져나감
        current_weight -= bridge.popleft()

        # 다음 트럭이 올라갈 수 있는지 확인
        if trucks:
            if current_weight + trucks[0] <= weight:
                truck = trucks.popleft()
                bridge.append(truck)
                current_weight += truck
            else:
                bridge.append(0)

    return time
