import java.util.Stack;

class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < ingredient.length; i++) {
            stack.push(ingredient[i]);  // 재료를 스택에 쌓음
            
            // 스택 상단 4개 요소가 [1, 2, 3, 1]이면 햄버거를 만들 수 있음
            if (stack.size() >= 4) {
                int size = stack.size();
                // 햄버거 순서가 맞는지 확인
                if (stack.get(size - 4) == 1 && stack.get(size - 3) == 2 && 
                    stack.get(size - 2) == 3 && stack.get(size - 1) == 1) {
                    // 스택에서 햄버거에 해당하는 재료 4개를 제거
                    stack.pop();
                    stack.pop();
                    stack.pop();
                    stack.pop();
                    
                    // 햄버거 하나 카운트
                    answer++;
                }
            }
        }
        
        return answer;
    }
}
