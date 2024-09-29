import java.util.Arrays;

class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;

        // 사과 점수를 내림차순으로 정렬
        Arrays.sort(score);
        
        // 내림차순이 필요하므로 reverse 하여 진행
        int length = score.length;
        
        // 뒤에서부터 m개씩 묶어서 계산
        for (int i = length - m; i >= 0; i -= m) {
            // m개씩 묶인 상자의 최저 점수는 해당 묶음의 마지막 사과
            int p = score[i];
            answer += p * m;
        }
        
        return answer;
    }
}
