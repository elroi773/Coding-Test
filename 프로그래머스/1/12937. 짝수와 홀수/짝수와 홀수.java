class Solution {
    public String solution(int num) {
        // num이 2로 나누어 떨어지면 짝수(Even), 그렇지 않으면 홀수(Odd)
        if (num % 2 == 0) {
            return "Even";
        } else {
            return "Odd";
        }
    }
}
