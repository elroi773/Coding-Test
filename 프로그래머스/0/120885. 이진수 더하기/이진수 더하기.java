class Solution {
    public String solution(String bin1, String bin2) {
        // 이진수 문자열을 정수로 변환
        int num1 = Integer.parseInt(bin1, 2);
        int num2 = Integer.parseInt(bin2, 2);
        
        // 두 정수를 더함
        int sum = num1 + num2;
        
        // 결과를 이진수 문자열로 변환
        String answer = Integer.toBinaryString(sum);
        
        return answer;
    }
}
