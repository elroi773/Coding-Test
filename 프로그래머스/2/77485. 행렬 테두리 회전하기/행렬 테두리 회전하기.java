class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[][] matrix = new int[rows][columns];
        int[] answer = new int[queries.length];

        // 행렬 초기화
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = i * columns + j + 1;
            }
        }

        for (int q = 0; q < queries.length; q++) {
            int x1 = queries[q][0] - 1;
            int y1 = queries[q][1] - 1;
            int x2 = queries[q][2] - 1;
            int y2 = queries[q][3] - 1;

            int prev = matrix[x1][y1];
            int minVal = prev;

            // 위쪽 행 (왼쪽 → 오른쪽)
            for (int j = y1 + 1; j <= y2; j++) {
                int temp = matrix[x1][j];
                matrix[x1][j] = prev;
                prev = temp;
                minVal = Math.min(minVal, prev);
            }

            // 오른쪽 열 (위 → 아래)
            for (int i = x1 + 1; i <= x2; i++) {
                int temp = matrix[i][y2];
                matrix[i][y2] = prev;
                prev = temp;
                minVal = Math.min(minVal, prev);
            }

            // 아래쪽 행 (오른쪽 → 왼쪽)
            for (int j = y2 - 1; j >= y1; j--) {
                int temp = matrix[x2][j];
                matrix[x2][j] = prev;
                prev = temp;
                minVal = Math.min(minVal, prev);
            }

            // 왼쪽 열 (아래 → 위)
            for (int i = x2 - 1; i >= x1; i--) {
                int temp = matrix[i][y1];
                matrix[i][y1] = prev;
                prev = temp;
                minVal = Math.min(minVal, prev);
            }

            answer[q] = minVal;
        }

        return answer;
    }
}