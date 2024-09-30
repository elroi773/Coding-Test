import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int[] answers) {
        // 각 수포자의 답안 패턴을 정의합니다.
        int[] first = {1, 2, 3, 4, 5};
        int[] second = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] third = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        // 각 수포자의 점수를 저장할 배열
        int[] scores = new int[3];
        
        // 정답 배열과 비교하여 각 수포자의 맞힌 개수를 계산합니다.
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == first[i % first.length]) scores[0]++;
            if (answers[i] == second[i % second.length]) scores[1]++;
            if (answers[i] == third[i % third.length]) scores[2]++;
        }
        
        // 가장 높은 점수를 찾습니다.
        int maxScore = Math.max(scores[0], Math.max(scores[1], scores[2]));
        
        // 가장 높은 점수를 받은 수포자를 리스트에 추가합니다.
        List<Integer> best = new ArrayList<>();
        if (scores[0] == maxScore) best.add(1);
        if (scores[1] == maxScore) best.add(2);
        if (scores[2] == maxScore) best.add(3);
        
        // 리스트를 배열로 변환하여 반환합니다.
        return best.stream().mapToInt(i -> i).toArray();
    }
}
