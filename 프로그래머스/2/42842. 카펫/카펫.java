class Solution {
    public int[] solution(int brown, int yellow) {
        
        // yellow = (w - 2) * (h - 2)
        // brown = 2w + 2h - 4
        
        for (int h = 1; h <= yellow; h++) {
            if (yellow % h != 0) continue;   // 나누어떨어지는 경우만
            
            int w = yellow / h;              // 내부 영역의 가로
            
            int totalW = w + 2;  // 전체 가로
            int totalH = h + 2;  // 전체 세로
            
            // brown 개수 조건 검사
            if (2 * totalW + 2 * totalH - 4 == brown) {
                return new int[] { totalW, totalH };
            }
        }
        
        return new int[0];
    }
}
