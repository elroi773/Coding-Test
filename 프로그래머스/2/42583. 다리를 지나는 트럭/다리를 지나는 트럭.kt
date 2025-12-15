import java.util.ArrayDeque

class Solution {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        val bridge = ArrayDeque<Int>()
        val trucks = ArrayDeque<Int>()

        // 대기 트럭 큐
        for (t in truck_weights) {
            trucks.addLast(t)
        }

        // 다리를 0으로 초기화
        repeat(bridge_length) {
            bridge.addLast(0)
        }

        var time = 0
        var currentWeight = 0

        while (bridge.isNotEmpty()) {
            time++

            // 트럭이 다리를 빠져나감
            currentWeight -= bridge.removeFirst()

            // 다음 트럭이 올라갈 수 있는지 확인
            if (trucks.isNotEmpty()) {
                if (currentWeight + trucks.first <= weight) {
                    val truck = trucks.removeFirst()
                    bridge.addLast(truck)
                    currentWeight += truck
                } else {
                    bridge.addLast(0)
                }
            }
        }

        return time
    }
}
