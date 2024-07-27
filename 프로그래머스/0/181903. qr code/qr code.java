class Solution {
    public String solution(int q, int r, String code) {
        StringBuilder result = new StringBuilder();
        
        // 문자열 code의 각 인덱스에 대해 검사
        for (int i = 0; i < code.length(); i++) {
            // 인덱스를 q로 나눈 나머지가 r인 경우
            if (i % q == r) {
                // 해당 인덱스의 문자를 result에 추가
                result.append(code.charAt(i));
            }
        }
        
        // 결과 문자열을 반환
        return result.toString();
    }
}
