class Solution {
    public String[] solution(String my_string) {
        // 정규 표현식을 사용하여 하나 이상의 공백을 기준으로 문자열을 분할
        String[] words = my_string.trim().split("\\s+");
        return words;
    }
}
