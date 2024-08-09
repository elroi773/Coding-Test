class Solution {
    public int[][] solution(int[][] arr) {
        int numRows = arr.length;  
        int numCols = arr[0].length;  
        int targetSize = Math.max(numRows, numCols);

        int[][] answer = new int[targetSize][targetSize];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                answer[i][j] = arr[i][j];
            }
        }

        return answer;
    }
}
