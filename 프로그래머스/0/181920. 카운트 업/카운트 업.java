import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> solution(int start_num, int end_num) {
        List<Integer> answer = new ArrayList<>();
        
        // start_num부터 end_num까지 숫자를 차례로 리스트에 추가
        for (int i = start_num; i <= end_num; i++) {
            answer.add(i);
        }
        
        return answer;
    }
}
