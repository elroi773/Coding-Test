class Solution {
    public int solution(String s) {
        int answer = 1;

        for (int i = 0; i < s.length(); i++) {
            int odd = expand(s, i, i);       // 홀수 길이
            int even = expand(s, i, i + 1);  // 짝수 길이

            answer = Math.max(answer, odd);
            answer = Math.max(answer, even);
        }

        return answer;
    }

    private int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}