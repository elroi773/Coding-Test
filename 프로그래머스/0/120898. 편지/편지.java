class Solution {
    public int solution(String message) {
        // message의 길이를 구합니다.
        int length = message.length();
        
        // 각 문자가 차지하는 길이가 2cm이므로, 전체 길이는 message 길이에 2를 곱한 값입니다.
        int answer = length * 2;
        
        return answer;
    }
}
