class Solution {
    public int[] solution(String my_string) {
        // Initialize an array of size 52 for each letter A-Z and a-z
        int[] answer = new int[52];
        
        // Iterate over each character in the input string
        for (int i = 0; i < my_string.length(); i++) {
            char c = my_string.charAt(i);
            
            // Check if the character is an uppercase letter
            if (c >= 'A' && c <= 'Z') {
                // Increment the count for this uppercase letter
                answer[c - 'A']++;
            }
            // Check if the character is a lowercase letter
            else if (c >= 'a' && c <= 'z') {
                // Increment the count for this lowercase letter
                answer[c - 'a' + 26]++;
            }
        }
        
        // Return the array with the counts
        return answer;
    }
}

