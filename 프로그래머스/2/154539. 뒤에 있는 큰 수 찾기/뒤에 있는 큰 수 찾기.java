import java.util.Stack;

class Solution {
    public int[] solution(int[] numbers) {
        int n = numbers.length;
        int[] answer = new int[n];
        Stack<Integer> stack = new Stack<>(); // 인덱스를 저장할 스택

        // 초기값은 -1로 설정 (뒷 큰수가 없는 경우)
        for (int i = 0; i < n; i++) {
            answer[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            // 현재 값이 스택 최상단 값보다 크면, 뒷 큰수를 채움
            while (!stack.isEmpty() && numbers[i] > numbers[stack.peek()]) {
                int idx = stack.pop();
                answer[idx] = numbers[i];
            }
            stack.push(i); // 현재 인덱스를 스택에 추가
        }

        return answer;
    }
}