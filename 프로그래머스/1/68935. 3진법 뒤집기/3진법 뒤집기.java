class Solution {
    public int solution(int n) {
        // 1. n을 3진법으로 변환
        String ternary = Integer.toString(n, 3);
        
        // 2. 변환된 3진법 문자열을 뒤집기
        String reversedTernary = new StringBuilder(ternary).reverse().toString();
        
        // 3. 뒤집어진 문자열을 10진법으로 변환
        int answer = Integer.parseInt(reversedTernary, 3);
        
        // 4. 결과 반환
        return answer;
    }
}
