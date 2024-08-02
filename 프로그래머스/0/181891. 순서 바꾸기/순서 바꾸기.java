class Solution {
    public int[] solution(int[] num_list, int n) {
        int[] answer = new int[num_list.length]; // Initialize the result array

        // Copy elements from index n to the end of num_list into the start of answer
        for (int i = n; i < num_list.length; i++) {
            answer[i - n] = num_list[i];
        }

        // Copy the first n elements to the end of answer
        for (int i = 0; i < n; i++) {
            answer[num_list.length - n + i] = num_list[i];
        }

        return answer; // Return the rearranged array
    }
}
