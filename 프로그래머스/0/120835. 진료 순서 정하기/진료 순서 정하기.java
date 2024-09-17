import java.util.Arrays;
import java.util.HashMap;

class Solution {
    public int[] solution(int[] emergency) {
        // 응급도를 내림차순으로 정렬한 배열을 만듦
        int[] sorted = emergency.clone();
        Arrays.sort(sorted);
        
        // 큰 수가 앞으로 오도록 배열을 뒤집음
        for (int i = 0; i < sorted.length / 2; i++) {
            int temp = sorted[i];
            sorted[i] = sorted[sorted.length - 1 - i];
            sorted[sorted.length - 1 - i] = temp;
        }
        
        // 정렬된 응급도에 순위를 매기기 위한 Map 생성
        HashMap<Integer, Integer> rankMap = new HashMap<>();
        for (int i = 0; i < sorted.length; i++) {
            rankMap.put(sorted[i], i + 1); // 순위는 1부터 시작
        }
        
        // 원래 배열의 응급도를 순위로 변환
        int[] answer = new int[emergency.length];
        for (int i = 0; i < emergency.length; i++) {
            answer[i] = rankMap.get(emergency[i]);
        }
        
        return answer;
    }
}
