class Solution {
    public String solution(String myString, String pat) {
        String answer = "";  // Initialize the answer as an empty string

        // Iterate over the possible ending indices for the substrings
        for (int end = 0; end <= myString.length(); end++) {
            // Get the current substring from start to the current ending index
            String currentSubstring = myString.substring(0, end);
            
            // Check if the current substring ends with the given pattern 'pat'
            if (currentSubstring.endsWith(pat)) {
                // Update answer if the current substring is longer than the previously found one
                answer = currentSubstring;
            }
        }
        
        // Return the longest substring found that ends with 'pat'
        return answer;
    }
}
