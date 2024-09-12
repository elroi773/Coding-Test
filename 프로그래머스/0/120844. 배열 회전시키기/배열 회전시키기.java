class Solution {
    public int[] solution(int[] numbers, String direction) {
        int[] answer = new int[numbers.length];
        
        if (direction.equals("right")) {
            // 마지막 원소를 첫 번째로, 나머지를 한 칸씩 오른쪽으로
            answer[0] = numbers[numbers.length - 1];  // 마지막 원소를 첫 번째로
            for (int i = 1; i < numbers.length; i++) {
                answer[i] = numbers[i - 1];  // 나머지 원소를 오른쪽으로 한 칸 이동
            }
        } else if (direction.equals("left")) {
            // 첫 번째 원소를 마지막으로, 나머지를 한 칸씩 왼쪽으로
            answer[numbers.length - 1] = numbers[0];  // 첫 번째 원소를 마지막으로
            for (int i = 0; i < numbers.length - 1; i++) {
                answer[i] = numbers[i + 1];  // 나머지 원소를 왼쪽으로 한 칸 이동
            }
        }
        
        return answer;
    }
}
