class Solution {
    public int[] solution(int n, int s) {
        if (s < n) {
            return new int[] { -1 };
        }
        
        int[] answer = new int[n];
        int base = s / n;
        int remain = s % n;
        
        for (int i = 0; i < n; i++) {
            answer[i] = base;
        }
        
        for (int i = n - remain; i < n; i++) {
            answer[i] += 1;
        }
        
        return answer;
    }
}