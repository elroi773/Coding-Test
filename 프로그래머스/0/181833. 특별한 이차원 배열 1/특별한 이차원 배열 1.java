class Solution {
    public int[][] solution(int n) {
        int[][] arr = new int[n][n];
        
        // 배열의 각 요소를 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    arr[i][j] = 1; // 대각선 요소는 1로 설정
                } else {
                    arr[i][j] = 0; // 나머지 요소는 0으로 설정
                }
            }
        }
        
        return arr;
    }
}
