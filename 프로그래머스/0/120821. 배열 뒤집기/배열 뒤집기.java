class Solution {
    public int[] solution(int[] num_list) {
        // num_list의 길이만큼 새로운 배열을 생성
        int[] answer = new int[num_list.length];
        
        // num_list의 원소들을 거꾸로 answer 배열에 복사
        for (int i = 0; i < num_list.length; i++) {
            answer[i] = num_list[num_list.length - 1 - i];
        }
        
        return answer;
    }
}
