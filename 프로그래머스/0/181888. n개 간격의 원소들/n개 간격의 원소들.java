class Solution {
    public int[] solution(int[] num_list, int n) {
        // Calculate the number of elements in the result array
        int size = (num_list.length + n - 1) / n;
        int[] answer = new int[size];  // Initialize the result array
        
        // Iterate over num_list and fill answer array with elements at intervals of n
        for (int i = 0, j = 0; i < num_list.length; i += n, j++) {
            answer[j] = num_list[i];
        }
        
        // Return the result array
        return answer;
    }
}
