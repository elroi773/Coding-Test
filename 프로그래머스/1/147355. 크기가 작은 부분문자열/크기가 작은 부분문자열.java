class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        int pLength = p.length();
        long pNum = Long.parseLong(p);  // p를 숫자로 변환
        
        // t에서 p와 길이가 같은 부분 문자열을 추출하여 비교
        for (int i = 0; i <= t.length() - pLength; i++) {
            String subStr = t.substring(i, i + pLength);  // 부분 문자열 추출
            long subNum = Long.parseLong(subStr);  // 부분 문자열을 숫자로 변환
            
            if (subNum <= pNum) {  // p보다 작거나 같으면
                answer++;
            }
        }
        
        return answer;
    }
}
