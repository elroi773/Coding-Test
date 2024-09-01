class Solution {
    public int solution(int i, int j, int k) {
        int answer = 0;
        String kStr = Integer.toString(k);
        
        for (int num = i; num <= j; num++) {
            String numStr = Integer.toString(num);
            for (int l = 0; l < numStr.length(); l++) {
                if (numStr.charAt(l) == kStr.charAt(0)) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}
