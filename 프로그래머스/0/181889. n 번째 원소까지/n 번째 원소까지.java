class Solution {
    public int[] solution(int[] num_list, int n) {
        // n개의 원소를 저장할 배열 생성
        int[] answer = new int[n];
        
        // num_list의 처음 n개의 원소를 answer로 복사
        System.arraycopy(num_list, 0, answer, 0, n);
        
        return answer;
    }
}
