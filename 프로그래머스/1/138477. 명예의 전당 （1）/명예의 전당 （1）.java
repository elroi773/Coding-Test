import java.util.PriorityQueue;

class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        PriorityQueue<Integer> hallOfFame = new PriorityQueue<>();
        
        for (int i = 0; i < score.length; i++) {
            // 새로운 점수를 명예의 전당에 추가
            hallOfFame.offer(score[i]);
            
            // 명예의 전당에 k개 이상의 점수가 있으면 최하위 점수를 제거
            if (hallOfFame.size() > k) {
                hallOfFame.poll();
            }
            
            // 매일 발표되는 최하위 점수 기록 (최소 힙의 첫 번째 요소)
            answer[i] = hallOfFame.peek();
        }
        
        return answer;
    }
}
