import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new LinkedList<>();
        Queue<Integer> trucks = new LinkedList<>();

        // 대기 트럭 큐에 추가
        for (int t : truck_weights) {
            trucks.offer(t);
        }

        // 다리를 0으로 초기화
        for (int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }

        int time = 0;
        int currentWeight = 0;

        while (!bridge.isEmpty()) {
            time++;

            // 다리에서 트럭 한 대 이동 완료
            currentWeight -= bridge.poll();

            // 다음 트럭을 올릴 수 있는지 확인
            if (!trucks.isEmpty()) {
                if (currentWeight + trucks.peek() <= weight) {
                    int truck = trucks.poll();
                    bridge.offer(truck);
                    currentWeight += truck;
                } else {
                    bridge.offer(0);
                }
            }
        }

        return time;
    }
}
