class Solution {
    public String solution(String[] my_strings, int[][] parts) {
        StringBuilder result = new StringBuilder();

        // Iterate over each string and its corresponding parts
        for (int i = 0; i < my_strings.length; i++) {
            String currentString = my_strings[i];
            int start = parts[i][0];
            int end = parts[i][1];

            // Extract the substring from start to end (inclusive)
            // Note: substring's end index is exclusive, so we use end + 1
            String substring = currentString.substring(start, end + 1);
            
            // Append the extracted substring to the result
            result.append(substring);
        }

        // Convert StringBuilder to a String and return
        return result.toString();
    }
}
