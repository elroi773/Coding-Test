class Solution {
    public int[] solution(long n) {
        // 숫자를 문자열로 변환
        String str = Long.toString(n);
        
        // 문자열을 뒤집고 각 자릿수를 배열에 저장
        int[] answer = new int[str.length()];
        
        for (int i = 0; i < str.length(); i++) {
            // 뒤에서부터 각 자릿수를 추출하여 배열에 저장
            answer[i] = str.charAt(str.length() - 1 - i) - '0';
        }
        
        return answer;
    }
}
