class Solution {
    public int solution(int[][] dots) {
        // x와 y의 최댓값, 최솟값을 구하기 위한 변수 초기화
        int xMin = dots[0][0], xMax = dots[0][0];
        int yMin = dots[0][1], yMax = dots[0][1];
        
        // 모든 점을 순회하며 최솟값과 최댓값을 찾는다
        for (int[] dot : dots) {
            if (dot[0] < xMin) xMin = dot[0];
            if (dot[0] > xMax) xMax = dot[0];
            if (dot[1] < yMin) yMin = dot[1];
            if (dot[1] > yMax) yMax = dot[1];
        }
        
        // 가로 길이와 세로 길이 계산
        int width = xMax - xMin;
        int height = yMax - yMin;
        
        // 넓이 계산
        return width * height;
    }
}
