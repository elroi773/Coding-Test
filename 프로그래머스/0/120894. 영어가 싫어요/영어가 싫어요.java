class Solution {
    public long solution(String numbers) {
        // 각 숫자 단어에 해당하는 숫자를 매핑
        String[] numWords = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] numDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        
        // 주어진 문자열에서 숫자 단어를 숫자로 치환
        for (int i = 0; i < numWords.length; i++) {
            numbers = numbers.replace(numWords[i], numDigits[i]);
        }
        
        // 치환된 문자열을 정수로 변환하여 반환
        return Long.parseLong(numbers);
    }
}
