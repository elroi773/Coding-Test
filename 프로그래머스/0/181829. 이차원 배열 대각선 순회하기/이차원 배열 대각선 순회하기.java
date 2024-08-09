class Solution {
    public int solution(int[][] board, int k) {
        int answer = 0;
        int rows = board.length;       // board의 행 길이
        int cols = board[0].length;    // board의 열 길이
        
        // 2차원 배열을 순회하면서 조건에 맞는 원소의 합을 구함
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i + j <= k) {
                    answer += board[i][j];
                }
            }
        }
        
        return answer;
    }
}
