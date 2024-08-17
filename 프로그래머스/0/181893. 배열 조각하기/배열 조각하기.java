class Solution {
    public int[] solution(int[] arr, int[] query) {
        for (int i = 0; i < query.length; i++) {
            if (i % 2 == 0) {  // 짝수 인덱스
                arr = java.util.Arrays.copyOfRange(arr, 0, query[i] + 1);
            } else {  // 홀수 인덱스
                arr = java.util.Arrays.copyOfRange(arr, query[i], arr.length);
            }
        }
        return arr;
    }
}
