class Solution {
    public int[] solution(int n, int k) {
        // Calculate the number of multiples of k from 1 to n
        int count = n / k;
        
        // Initialize the answer array with the calculated count
        int[] answer = new int[count];
        
        // Populate the answer array with multiples of k
        for (int i = 1; i <= count; i++) {
            answer[i - 1] = i * k;
        }
        
        // Return the array with the multiples of k
        return answer;
    }
}
