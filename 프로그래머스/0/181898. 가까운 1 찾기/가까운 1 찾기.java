class Solution {
    public int solution(int[] arr, int idx) {
        // Check if the current index already has the value 1
        if (arr[idx] == 1) {
            return idx;
        }

        // Iterate from idx + 1 to find the next occurrence of 1
        for (int i = idx + 1; i < arr.length; i++) {
            if (arr[i] == 1) {
                return i; // Return the index of the first 1 found
            }
        }

        // If no 1 is found after idx, return -1
        return -1;
    }
}
