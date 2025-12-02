import java.util.*;

class Solution {
    public int solution(String s) {
        int n = s.length();
        int answer = 0;
        
        for (int x = 0; x < n; x++) {
            String rotated = s.substring(x) + s.substring(0, x);
            if (isValid(rotated)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private boolean isValid(String str) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : str.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                
                char top = stack.pop();
                
                if (c == ')' && top != '(') return false;
                if (c == ']' && top != '[') return false;
                if (c == '}' && top != '{') return false;
            }
        }
        
        return stack.isEmpty();
    }
}
