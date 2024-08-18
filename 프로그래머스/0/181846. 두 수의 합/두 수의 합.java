class Solution {
    public String solution(String a, String b) {
        StringBuilder result = new StringBuilder();
        
        int carry = 0; // 올림 값을 저장할 변수
        int aLen = a.length();
        int bLen = b.length();
        
        // 뒤에서부터 한 자리씩 더하기
        int i = aLen - 1;
        int j = bLen - 1;
        
        while (i >= 0 || j >= 0 || carry != 0) {
            int digitA = (i >= 0) ? a.charAt(i) - '0' : 0;
            int digitB = (j >= 0) ? b.charAt(j) - '0' : 0;
            
            int sum = digitA + digitB + carry;
            carry = sum / 10; // 10을 넘어가면 올림 발생
            result.append(sum % 10); // 결과 문자열에 추가
            
            i--;
            j--;
        }
        
        // 결과가 거꾸로 되어있으므로 뒤집어야 함
        return result.reverse().toString();
    }
}
