import java.util.Arrays;

class Solution {
    public int[] solution(int[] num_list) {
        // num_list를 복사해서 정렬합니다.
        int[] sortedList = num_list.clone();
        Arrays.sort(sortedList);
        
        // 가장 작은 5개의 수를 저장할 배열을 생성합니다.
        int[] answer = new int[5];
        
        // 정렬된 배열에서 처음 5개의 원소를 선택하여 answer에 복사합니다.
        for (int i = 0; i < 5; i++) {
            answer[i] = sortedList[i];
        }
        
        // 오름차순으로 이미 정렬되어 있으므로 그대로 반환합니다.
        return answer;
    }
}
