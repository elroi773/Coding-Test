class Solution {
    public int[] solution(String[] strlist) {
        // 1. strlist의 길이와 동일한 크기의 정수 배열 생성
        int[] answer = new int[strlist.length];
        
        // 2. 각 문자열의 길이를 answer 배열에 저장
        for (int i = 0; i < strlist.length; i++) {
            answer[i] = strlist[i].length();
        }
        
        // 3. 결과 배열 반환
        return answer;
    }
}
