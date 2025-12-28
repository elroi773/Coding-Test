class Solution {
    public long solution(int[] sequence) {
        long maxSum = 0;

        long curr1 = 0; // [1, -1, 1, -1 ...]
        long curr2 = 0; // [-1, 1, -1, 1 ...]

        for (int i = 0; i < sequence.length; i++) {
            long sign1 = (i % 2 == 0) ? 1 : -1;
            long sign2 = -sign1;

            long v1 = sequence[i] * sign1;
            long v2 = sequence[i] * sign2;

            curr1 = Math.max(v1, curr1 + v1);
            curr2 = Math.max(v2, curr2 + v2);

            maxSum = Math.max(maxSum, Math.max(curr1, curr2));
        }

        return maxSum;
    }
}
