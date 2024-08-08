class Solution {
    public String solution(String myString) {
        StringBuilder answer = new StringBuilder();
        
        for (char ch : myString.toCharArray()) {
            if (ch < 'l') {
                answer.append('l');
            } else {
                answer.append(ch);
            }
        }
        
        return answer.toString();
    }
}
