class Solution {
    public int solution(int[] array) {
        int answer = 0;
        
        for (int num : array) {
            String str = Integer.toString(num);
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '7') {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}
