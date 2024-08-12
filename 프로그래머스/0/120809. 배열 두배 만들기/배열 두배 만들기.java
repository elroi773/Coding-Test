class Solution {
    public int[] solution(int[] numbers) {
        // numbers 배열과 같은 크기의 새로운 배열을 생성합니다.
        int[] answer = new int[numbers.length];
        
        // numbers 배열을 순회하면서 각 원소를 두 배로 만들어 answer 배열에 저장합니다.
        for (int i = 0; i < numbers.length; i++) {
            answer[i] = numbers[i] * 2;
        }
        
        // 두 배로 변환된 배열을 반환합니다.
        return answer;
    }
}
