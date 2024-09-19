class Solution {
    public int solution(int[][] board) {
        int n = board.length;
        // 8방향 탐색을 위한 배열 (상, 하, 좌, 우, 대각선 4방향)
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        
        // 위험지역을 표시하기 위한 새로운 배열 생성
        boolean[][] danger = new boolean[n][n];
        
        // 지뢰를 찾고 그 주변을 위험지역으로 표시
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) { // 지뢰 발견
                    danger[i][j] = true; // 지뢰가 있는 곳도 위험지역
                    
                    // 지뢰 주변 8방향을 탐색
                    for (int d = 0; d < 8; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        
                        // 배열 범위 밖을 벗어나지 않도록 체크
                        if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                            danger[nx][ny] = true;
                        }
                    }
                }
            }
        }
        
        // 안전한 지역 카운트
        int safeCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!danger[i][j]) { // 위험하지 않은 지역 카운트
                    safeCount++;
                }
            }
        }
        
        return safeCount;
    }
}
