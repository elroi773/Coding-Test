class Solution {
    public String solution(String my_string, int[] indices) {
        StringBuilder answer = new StringBuilder();
        boolean[] toRemove = new boolean[my_string.length()];
        
        // Mark the indices to remove
        for (int index : indices) {
            toRemove[index] = true;
        }
        
        // Build the answer string by skipping the marked indices
        for (int i = 0; i < my_string.length(); i++) {
            if (!toRemove[i]) {
                answer.append(my_string.charAt(i));
            }
        }
        
        return answer.toString();
    }
}
