class Solution {
    public int[] solution(int n, long left, long right) {
        int size = (int)(right - left + 1);
        int[] answer = new int[size];

        for (long i = left; i <= right; i++) {
            long row = i / n;
            long col = i % n;
            answer[(int)(i - left)] = (int)(Math.max(row, col) + 1);
        }

        return answer;
    }
}
