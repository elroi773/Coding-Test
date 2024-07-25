class Solution {
    public String solution(String my_string, int[][] queries) {
        // Convert the input string to a mutable data structure like StringBuilder
        StringBuilder sb = new StringBuilder(my_string);

        // Iterate through each query
        for (int[] query : queries) {
            int start = query[0];
            int end = query[1];

            // Reverse the segment of the string from start to end
            while (start < end) {
                // Swap characters at positions start and end
                char temp = sb.charAt(start);
                sb.setCharAt(start, sb.charAt(end));
                sb.setCharAt(end, temp);

                // Move towards the center
                start++;
                end--;
            }
        }

        // Convert the StringBuilder back to a String and return
        return sb.toString();
    }
}
