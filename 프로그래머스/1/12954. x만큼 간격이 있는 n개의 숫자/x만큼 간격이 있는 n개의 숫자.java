class Solution {
    public long[] solution(int x, int n) {
        long[] answer = new long[n];
        long currentValue = x;
        
        for(int i =0; i<n; i++){
            answer[i] = currentValue;
            currentValue += x;
        }
        return answer;
    }
}