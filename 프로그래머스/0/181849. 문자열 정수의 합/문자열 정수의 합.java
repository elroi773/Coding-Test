class Solution {
    public int solution(String num_str) {
        int sum = 0;
        for (int i = 0; i < num_str.length(); i++) {
            // 각 문자를 숫자로 변환하여 더하기
            sum += Character.getNumericValue(num_str.charAt(i));
        }
        return sum;
    }
}
