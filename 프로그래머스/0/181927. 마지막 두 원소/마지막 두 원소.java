class Solution {
    public int[] solution(int[] num_list) {
        // 새로운 배열의 길이는 기존 배열보다 1만큼 더 큽니다.
        int[] answer = new int[num_list.length + 1];
        
        // 기존 배열의 원소를 새로운 배열로 복사합니다.
        for (int i = 0; i < num_list.length; i++) {
            answer[i] = num_list[i];
        }
        
        // 마지막 원소와 그 전 원소를 비교합니다.
        if (num_list[num_list.length - 1] > num_list[num_list.length - 2]) {
            // 마지막 원소가 그 전 원소보다 큰 경우
            answer[answer.length - 1] = num_list[num_list.length - 1] - num_list[num_list.length - 2];
        } else {
            // 그렇지 않은 경우
            answer[answer.length - 1] = num_list[num_list.length - 1] * 2;
        }
        
        return answer;
    }
}
