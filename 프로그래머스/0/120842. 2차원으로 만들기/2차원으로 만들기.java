class Solution {
    public int[][] solution(int[] num_list, int n) {
        // 행의 개수 계산
        int rows = num_list.length / n;
        // 결과를 담을 2차원 배열 선언
        int[][] answer = new int[rows][n];
        
        // num_list를 2차원 배열로 변환
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < n; j++) {
                // 1차원 배열의 값을 2차원 배열에 채움
                answer[i][j] = num_list[i * n + j];
            }
        }
        
        return answer;
    }
}
