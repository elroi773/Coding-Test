class Solution {
    public int solution(String myString, String pat) {
        // 소문자로 변환하여 비교
        String lowerMyString = myString.toLowerCase();
        String lowerPat = pat.toLowerCase();
        
        // myString에 pat이 포함되어 있는지 확인
        if (lowerMyString.contains(lowerPat)) {
            return 1; // 존재하면 1 반환
        } else {
            return 0; // 존재하지 않으면 0 반환
        }
    }
}
