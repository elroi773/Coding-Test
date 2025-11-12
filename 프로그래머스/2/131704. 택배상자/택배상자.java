import java.util.Stack;

class Solution {
    public int solution(int[] order) {
        Stack<Integer> stack = new Stack<>();
        int current = 1;   // 컨테이너 벨트에서 꺼낼 상자 번호
        int answer = 0;    // 트럭에 실은 상자 개수

        for (int target : order) {
            // 현재 필요한 상자가 나올 때까지 컨테이너에서 상자 꺼내 스택에 보관
            while (current <= order.length && (stack.isEmpty() || stack.peek() != target)) {
                stack.push(current);
                current++;
            }

            // 스택의 맨 위가 필요한 상자라면 트럭에 실음
            if (!stack.isEmpty() && stack.peek() == target) {
                stack.pop();
                answer++;
            } else {
                break; // 더 이상 실을 수 없음
            }
        }

        return answer;
    }
}
