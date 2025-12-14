import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> result = new ArrayList<>();
        
        // 1. 각 작업 완료까지 걸리는 날짜 계산
        int[] days = new int[progresses.length];
        for (int i = 0; i < progresses.length; i++) {
            days[i] = (100 - progresses[i] + speeds[i] - 1) / speeds[i];
        }
        
        // 2. 배포 묶기
        int currentDay = days[0];
        int count = 1;
        
        for (int i = 1; i < days.length; i++) {
            if (days[i] <= currentDay) {
                count++;
            } else {
                result.add(count);
                currentDay = days[i];
                count = 1;
            }
        }
        
        // 마지막 배포
        result.add(count);
        
        // List → int[]
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}
