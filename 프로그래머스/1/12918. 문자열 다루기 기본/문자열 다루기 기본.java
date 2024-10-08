class Solution {
    public boolean solution(String s) {
        // 길이가 4 또는 6이 아니면 false 반환
        if (s.length() != 4 && s.length() != 6) {
            return false;
        }
        
        // 모든 문자 검사하여 숫자인지 확인
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false; // 숫자가 아닌 문자가 있으면 false 반환
            }
        }
        
        return true; // 모든 조건을 만족하면 true 반환
    }
}
