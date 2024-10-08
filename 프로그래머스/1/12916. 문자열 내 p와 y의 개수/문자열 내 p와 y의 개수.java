class Solution {
    boolean solution(String s) {
        // 문자열을 소문자로 변환
        s = s.toLowerCase();

        // 'p'와 'y'의 개수를 셈
        int countP = 0;
        int countY = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'p') {
                countP++;
            } else if (c == 'y') {
                countY++;
            }
        }

        // 'p'와 'y'의 개수가 같은지 확인
        return countP == countY;
    }
}
