class Solution {
    public int[] solution(int[] sequence, int k) {
        int n = sequence.length;
        int start = 0, end = 0;
        int currentSum = sequence[0];

        // 초기값: 최대 길이로 설정
        int minLen = n;
        int[] answer = new int[]{0, n - 1};

        while (start < n && end < n) {
            if (currentSum < k) {
                end++;
                if (end < n) {
                    currentSum += sequence[end];
                }
            } else if (currentSum > k) {
                currentSum -= sequence[start];
                start++;
            } else { // currentSum == k
                if ((end - start) < minLen) {
                    minLen = end - start;
                    answer[0] = start;
                    answer[1] = end;
                }
                currentSum -= sequence[start];
                start++;
            }
        }

        return answer;
    }
}