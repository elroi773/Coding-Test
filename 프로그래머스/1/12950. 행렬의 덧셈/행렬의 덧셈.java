class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int rows = arr1.length; // 행의 개수
        int cols = arr1[0].length; // 열의 개수

        // 결과를 저장할 행렬 초기화
        int[][] answer = new int[rows][cols];

        // 행렬 덧셈 수행
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                answer[i][j] = arr1[i][j] + arr2[i][j]; // 같은 위치의 값 더하기
            }
        }

        return answer; // 결과 반환
    }
}
