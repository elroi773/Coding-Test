import java.util.Stack;

class Solution {
    public int solution(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            // 스택이 비어있지 않고, top이 현재 문자와 같으면 제거
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        
        // 스택이 비어 있으면 성공
        return stack.isEmpty() ? 1 : 0;
    }
}
