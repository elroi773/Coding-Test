class Solution {
    public int solution(String my_string) {
        // 문자열을 공백으로 분리하여 배열로 변환
        String[] tokens = my_string.split(" ");
        
        // 첫 번째 숫자를 초기값으로 설정
        int result = Integer.parseInt(tokens[0]);
        
        // 연산자와 숫자를 순차적으로 계산
        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            int number = Integer.parseInt(tokens[i + 1]);
            
            if (operator.equals("+")) {
                result += number;
            } else if (operator.equals("-")) {
                result -= number;
            }
        }
        
        // 최종 계산된 값을 반환
        return result;
    }
}
