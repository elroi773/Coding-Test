import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        // 이름과 그리움 점수를 매핑하는 해시맵
        Map<String, Integer> yearningMap = new HashMap<>();
        
        // name과 yearning을 매핑
        for (int i = 0; i < name.length; i++) {
            yearningMap.put(name[i], yearning[i]);
        }
        
        // 각 사진의 추억 점수를 저장할 배열
        int[] answer = new int[photo.length];
        
        // 각 사진에 대한 점수 계산
        for (int i = 0; i < photo.length; i++) {
            int score = 0;
            for (String person : photo[i]) {
                // 그리움 점수가 있으면 더하고, 없으면 0점을 더함
                score += yearningMap.getOrDefault(person, 0);
            }
            // 현재 사진의 추억 점수를 저장
            answer[i] = score;
        }
        
        return answer;
    }
}
