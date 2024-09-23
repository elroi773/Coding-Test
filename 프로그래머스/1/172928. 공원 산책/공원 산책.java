class Solution {
    public int[] solution(String[] park, String[] routes) {
        // 공원의 높이와 너비
        int H = park.length;
        int W = park[0].length();
        
        // 시작 위치를 찾습니다.
        int[] position = new int[2];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (park[i].charAt(j) == 'S') {
                    position[0] = i;  // 세로 좌표
                    position[1] = j;  // 가로 좌표
                    break;
                }
            }
        }
        
        // 방향에 따른 좌표 변화를 정의합니다.
        int[] dRow = { -1, 1, 0, 0 };  // N, S, W, E에 해당하는 세로 변화
        int[] dCol = { 0, 0, -1, 1 };  // N, S, W, E에 해당하는 가로 변화
        String directions = "NSWE";
        
        // 명령을 하나씩 처리합니다.
        for (String route : routes) {
            String[] parts = route.split(" ");
            char direction = parts[0].charAt(0);
            int distance = Integer.parseInt(parts[1]);
            
            int dirIdx = directions.indexOf(direction);
            int newRow = position[0];
            int newCol = position[1];
            
            // 이동 가능 여부를 확인하며 이동합니다.
            boolean canMove = true;
            for (int step = 1; step <= distance; step++) {
                newRow += dRow[dirIdx];
                newCol += dCol[dirIdx];
                
                // 공원의 경계를 벗어나는지 확인
                if (newRow < 0 || newRow >= H || newCol < 0 || newCol >= W || park[newRow].charAt(newCol) == 'X') {
                    canMove = false;
                    break;
                }
            }
            
            // 이동 가능하다면 위치를 업데이트합니다.
            if (canMove) {
                position[0] = newRow;
                position[1] = newCol;
            }
        }
        
        // 최종 위치 반환
        return position;
    }
}
