class Solution {
    public int solution(int[] num_list) {
        int totalOperations = 0;

        for (int num : num_list) {
            int operations = 0;

            while (num != 1) {
                if (num % 2 == 0) {
                    num /= 2;
                } else {
                    num = (num - 1) / 2;
                }
                operations++;
            }

            totalOperations += operations;
        }

        return totalOperations;
    }
}
