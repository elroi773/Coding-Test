import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int baseTime = fees[0];  // 기본 시간(분)
        int baseFee  = fees[1];  // 기본 요금(원)
        int unitTime = fees[2];  // 단위 시간(분)
        int unitFee  = fees[3];  // 단위 요금(원)

        // 차량별 현재 입차 시간 (주차 중이면 값 존재, 아니면 없음)
        Map<String, Integer> inMap = new HashMap<>();
        // 차량별 누적 주차 시간
        Map<String, Integer> totalTime = new HashMap<>();

        // "HH:MM" -> 분
        java.util.function.Function<String, Integer> timeToMinutes = t -> {
            String[] parts = t.split(":");
            int hh = Integer.parseInt(parts[0]);
            int mm = Integer.parseInt(parts[1]);
            return hh * 60 + mm;
        };

        // 1. 입/출차 기록 처리
        for (String record : records) {
            String[] parts = record.split(" ");
            String timeStr = parts[0];
            String carNum  = parts[1];
            String type    = parts[2];

            int minutes = timeToMinutes.apply(timeStr);

            if (type.equals("IN")) {
                inMap.put(carNum, minutes);
            } else { // OUT
                int inTime = inMap.remove(carNum);  // 항상 존재한다고 가정
                int diff = minutes - inTime;
                totalTime.put(carNum, totalTime.getOrDefault(carNum, 0) + diff);
            }
        }

        // 2. 아직 출차 안 한 차량들 23:59 처리
        int endOfDay = 23 * 60 + 59;
        for (Map.Entry<String, Integer> entry : inMap.entrySet()) {
            String carNum = entry.getKey();
            int inTime = entry.getValue();
            int diff = endOfDay - inTime;
            totalTime.put(carNum, totalTime.getOrDefault(carNum, 0) + diff);
        }

        // 3. 요금 계산 함수
        java.util.function.IntFunction<Integer> calcFee = time -> {
            if (time <= baseTime) return baseFee;
            int extra = time - baseTime;
            int units = (extra + unitTime - 1) / unitTime; // 올림
            return baseFee + units * unitFee;
        };

        // 4. 차량 번호 오름차순 정렬
        List<String> cars = new ArrayList<>(totalTime.keySet());
        Collections.sort(cars); // 문자열 정렬 = 차량번호 오름차순

        // 5. 결과 배열 생성
        int[] answer = new int[cars.size()];
        for (int i = 0; i < cars.size(); i++) {
            String car = cars.get(i);
            int t = totalTime.get(car);
            answer[i] = calcFee.apply(t);
        }

        return answer;
    }
}