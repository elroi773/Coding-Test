class Solution {
    public String solution(String str1, String str2) {
        StringBuilder answer = new StringBuilder();
        int length = str1.length();
        
        for (int i = 0; i < length; i++) {
            answer.append(str1.charAt(i));
            answer.append(str2.charAt(i));
        }
        
        return answer.toString();
    }
}
