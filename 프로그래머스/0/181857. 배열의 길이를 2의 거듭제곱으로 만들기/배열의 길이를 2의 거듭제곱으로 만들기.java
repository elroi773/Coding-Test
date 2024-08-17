class Solution {
    public int[] solution(int[] arr) {
        int n = arr.length;
        int power = 1;
        
        // 배열 길이 이상의 가장 작은 2의 거듭제곱을 찾음
        while (power < n) {
            power *= 2;
        }
        
        // 새로운 배열을 만들고, arr의 내용을 복사
        int[] answer = new int[power];
        for (int i = 0; i < n; i++) {
            answer[i] = arr[i];
        }
        
        return answer;
    }
}
