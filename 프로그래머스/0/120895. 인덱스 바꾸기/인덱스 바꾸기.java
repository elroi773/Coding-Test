class Solution {
    public String solution(String my_string, int num1, int num2) {
        // 문자열을 문자 배열로 변환
        char[] charArray = my_string.toCharArray();
        
        // 인덱스 num1과 num2의 문자 교환
        char temp = charArray[num1];
        charArray[num1] = charArray[num2];
        charArray[num2] = temp;
        
        // 문자 배열을 문자열로 변환하여 반환
        return new String(charArray);
    }
}
