class Solution {
    public int[] solution(String[] keyinput, int[] board) {
        int x = 0; // 초기 x 좌표
        int y = 0; // 초기 y 좌표
        
        // 보드의 경계 설정
        int xLimit = board[0] / 2;
        int yLimit = board[1] / 2;
        
        // 방향키 처리
        for (String key : keyinput) {
            switch (key) {
                case "up":
                    if (y < yLimit) y++; // 위로 이동
                    break;
                case "down":
                    if (y > -yLimit) y--; // 아래로 이동
                    break;
                case "left":
                    if (x > -xLimit) x--; // 왼쪽으로 이동
                    break;
                case "right":
                    if (x < xLimit) x++; // 오른쪽으로 이동
                    break;
            }
        }
        
        return new int[] { x, y }; // 최종 좌표 반환
    }
}
