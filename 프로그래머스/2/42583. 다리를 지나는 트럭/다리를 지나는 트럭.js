function solution(bridge_length, weight, truck_weights) {
    let bridge = Array(bridge_length).fill(0);
    let time = 0;
    let currentWeight = 0;

    while (bridge.length) {
        time++;

        // 트럭이 다리를 빠져나감
        currentWeight -= bridge.shift();

        // 다음 트럭이 올라갈 수 있는지 확인
        if (truck_weights.length) {
            if (currentWeight + truck_weights[0] <= weight) {
                const truck = truck_weights.shift();
                bridge.push(truck);
                currentWeight += truck;
            } else {
                bridge.push(0);
            }
        }
    }

    return time;
}
