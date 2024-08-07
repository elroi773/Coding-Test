class Solution {
    public int solution(int n) {
        int answer = 0;
        
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) { // Check if the number is even
                answer += i;  // Add the even number to the sum
            }
        }
        
        return answer; // Return the total sum of even numbers
    }
}
