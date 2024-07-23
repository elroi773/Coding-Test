class Solution {
    public int solution(String number) {
        int sumOfDigits = 0;

       // 문자열 number의 각 문자를 순회하며 숫자로 변환하여 합산
        for (char digit : number.toCharArray()) {
            sumOfDigits += digit - '0'; // 문자 '0'을 빼서 정수로 변환
        }

        // 자리 숫자의 합을 9로 나눈 나머지를 반환
        return sumOfDigits % 9;
    }
}
