class Solution {
    public int solution(String str1, String str2) {
        // str2가 str1을 포함하고 있는지 확인
        if (str2.contains(str1)) {
            return 1;
        } else {
            return 0;
        }
    }
}
