class Solution {
    public String solution(String my_string) {
        // StringBuilder를 사용하여 문자열을 뒤집는다
        StringBuilder sb = new StringBuilder(my_string);
        String reversedString = sb.reverse().toString();
        
        // 뒤집은 문자열을 반환
        return reversedString;
    }
}
