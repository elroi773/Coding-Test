class Solution {
    public int solution(int[] a, int[] b) {
        int answer = 0; // 결과를 저장할 변수
        
        // 배열의 길이만큼 반복하며 내적 계산
        for (int i = 0; i < a.length; i++) {
            answer += a[i] * b[i]; // 각 요소를 곱해서 더함
        }
        
        return answer; // 최종 결과 반환
    }
}
