class Solution {
    public int solution(int order) {
        int answer = 0;
        String orderStr = Integer.toString(order);
        
        // 각 자리에 대해 3, 6, 9가 있는지 확인
        for (int i = 0; i < orderStr.length(); i++) {
            char ch = orderStr.charAt(i);
            if (ch == '3' || ch == '6' || ch == '9') {
                answer++;
            }
        }
        
        return answer;
    }
}
