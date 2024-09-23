import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        // 선수들의 이름과 현재 등수를 기록할 맵
        Map<String, Integer> playerMap = new HashMap<>();
        
        // 초기 등수 기록
        for (int i = 0; i < players.length; i++) {
            playerMap.put(players[i], i);  // 선수 이름 -> 현재 등수
        }
        
        // callings에서 추월 상황 처리
        for (String calling : callings) {
            int currentIndex = playerMap.get(calling);  // 호출된 선수의 현재 등수
            int previousIndex = currentIndex - 1;  // 앞선 선수의 등수
            
            // 선수 위치 변경
            String previousPlayer = players[previousIndex];  // 앞선 선수의 이름
            
            // 순서 변경 (players 배열에서 교환)
            players[previousIndex] = calling;
            players[currentIndex] = previousPlayer;
            
            // 맵에서도 순서 업데이트
            playerMap.put(calling, previousIndex);
            playerMap.put(previousPlayer, currentIndex);
        }
        
        return players;
    }
}
