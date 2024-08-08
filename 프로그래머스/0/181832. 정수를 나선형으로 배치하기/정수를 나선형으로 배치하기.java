class Solution {
    public int[][] solution(int n) {
        int[][] answer = new int[n][n];
        
        int num = 1;
        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;

        while (num <= n * n) {
            // Left to Right
            for (int i = left; i <= right; i++) {
                answer[top][i] = num++;
            }
            top++;

            // Top to Bottom
            for (int i = top; i <= bottom; i++) {
                answer[i][right] = num++;
            }
            right--;

            // Right to Left
            for (int i = right; i >= left; i--) {
                answer[bottom][i] = num++;
            }
            bottom--;

            // Bottom to Top
            for (int i = bottom; i >= top; i--) {
                answer[i][left] = num++;
            }
            left++;
        }
        
        return answer;
    }
}
