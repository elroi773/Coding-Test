class Solution {
    public String solution(String my_string, String letter) {
        // 문자열에서 해당 문자를 빈 문자열로 대체하여 제거
        String answer = my_string.replace(letter, "");
        return answer;
    }
}
