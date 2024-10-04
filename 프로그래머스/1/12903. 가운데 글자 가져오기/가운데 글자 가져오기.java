class Solution {
    public String solution(String s) {
        int length = s.length(); // 문자열의 길이를 구합니다.
        String answer = "";
        
        // 길이가 홀수인 경우
        if (length % 2 == 1) {
            int middleIndex = length / 2; // 가운데 인덱스
            answer = String.valueOf(s.charAt(middleIndex)); // 가운데 글자
        } 
        // 길이가 짝수인 경우
        else {
            int middleIndex1 = length / 2 - 1; // 첫 번째 가운데 인덱스
            int middleIndex2 = length / 2; // 두 번째 가운데 인덱스
            answer = s.substring(middleIndex1, middleIndex2 + 1); // 가운데 두 글자
        }
        
        return answer; // 결과 반환
    }
}
