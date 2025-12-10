import java.util.*;

class Solution {
    public int solution(String dirs) {
        // 방향 이동 정의
        Map<Character, int[]> moves = new HashMap<>();
        moves.put('U', new int[]{0, 1});
        moves.put('D', new int[]{0, -1});
        moves.put('R', new int[]{1, 0});
        moves.put('L', new int[]{-1, 0});

        // 방문한 길(간선) 저장: "x1,y1,x2,y2" 형태로 기록
        Set<String> visited = new HashSet<>();

        int x = 0, y = 0; // 시작점

        for (char d : dirs.toCharArray()) {
            int[] move = moves.get(d);
            int nx = x + move[0];
            int ny = y + move[1];

            // 경계 체크
            if (nx < -5 || nx > 5 || ny < -5 || ny > 5) {
                continue;
            }

            // 길 기록 (양방향 모두 기록)
            String path = x + "," + y + "," + nx + "," + ny;
            String reversePath = nx + "," + ny + "," + x + "," + y;

            visited.add(path);
            visited.add(reversePath); // 역방향도 저장하여 중복 방지

            // 이동
            x = nx;
            y = ny;
        }

        // 양방향으로 저장했으므로 2로 나눔
        return visited.size() / 2;
    }
}
