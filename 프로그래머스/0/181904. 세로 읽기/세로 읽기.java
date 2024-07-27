class Solution {
    public String solution(String my_string, int m, int c) {
        StringBuilder result = new StringBuilder();
        
        // c는 1부터 시작한다고 가정하고, 이를 0-based index로 변환
        int colIndex = c - 1;
        
        // 총 몇 줄이 필요한지 계산
        int numberOfRows = my_string.length() / m;
        
        // 각 줄에서 c번째 열의 문자를 추출
        for (int i = 0; i < numberOfRows; i++) {
            // i번째 줄의 시작 인덱스는 i * m
            // c번째 열의 인덱스는 (i * m) + colIndex
            int charIndex = (i * m) + colIndex;
            result.append(my_string.charAt(charIndex));
        }
        
        // 결과 문자열을 반환
        return result.toString();
    }
}
