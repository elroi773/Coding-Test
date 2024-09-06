class Solution {
    public int solution(int[] box, int n) {
        // 각 차원에서 들어갈 수 있는 주사위의 개수를 구한 후 곱함
        int width = box[0] / n;  // 가로에서 들어갈 수 있는 주사위 개수
        int length = box[1] / n; // 세로에서 들어갈 수 있는 주사위 개수
        int height = box[2] / n; // 높이에서 들어갈 수 있는 주사위 개수
        
        // 총 주사위 개수는 가로, 세로, 높이의 개수를 곱한 값
        int answer = width * length * height;
        
        return answer;
    }
}
