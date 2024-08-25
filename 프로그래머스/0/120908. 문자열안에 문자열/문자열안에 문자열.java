class Solution {
    public int solution(String str1, String str2) {
        // str1 안에 str2가 포함되어 있는지 확인
        if (str1.contains(str2)) {
            return 1;
        } else {
            return 2;
        }
    }
}
