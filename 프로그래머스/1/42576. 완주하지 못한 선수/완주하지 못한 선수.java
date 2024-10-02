import java.util.HashMap;

class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> map = new HashMap<>();
        
        // 참가자 이름과 수를 해시맵에 저장
        for (String name : participant) {
            map.put(name, map.getOrDefault(name, 0) + 1);
        }
        
        // 완주한 참가자 이름을 해시맵에서 제거
        for (String name : completion) {
            map.put(name, map.get(name) - 1);
        }
        
        // 완주하지 못한 참가자를 찾기
        for (String name : map.keySet()) {
            if (map.get(name) > 0) {
                return name; // 완주하지 못한 선수의 이름 반환
            }
        }
        
        return ""; // 기본값 (이론적으로 도달하지 않아야 함)
    }
}
