import java.util.HashMap;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];  // 결과를 저장할 배열
        HashMap<Character, Integer> lastIndexMap = new HashMap<>();  // 각 문자의 마지막 위치를 저장할 맵
        
        // 문자열을 한 글자씩 확인
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            
            // 현재 문자가 이전에 등장했는지 확인
            if (lastIndexMap.containsKey(currentChar)) {
                // 이전에 등장했다면, 그 위치와의 차이를 저장
                answer[i] = i - lastIndexMap.get(currentChar);
            } else {
                // 처음 등장한 문자라면 -1 저장
                answer[i] = -1;
            }
            
            // 현재 문자의 위치를 마지막 등장 위치로 갱신
            lastIndexMap.put(currentChar, i);
        }
        
        return answer;
    }
}
