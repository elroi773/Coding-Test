class Solution {
    public int solution(int[][] sizes) {
        int maxWidth = 0;
        int maxHeight = 0;
        
        // 모든 명함을 탐색하면서 가로, 세로를 정렬
        for (int[] size : sizes) {
            int w = Math.max(size[0], size[1]);  // 큰 값을 가로로
            int h = Math.min(size[0], size[1]);  // 작은 값을 세로로
            
            // 가로와 세로의 최대값을 갱신
            maxWidth = Math.max(maxWidth, w);
            maxHeight = Math.max(maxHeight, h);
        }
        
        // 지갑 크기는 가장 큰 가로와 세로의 곱
        return maxWidth * maxHeight;
    }
}
