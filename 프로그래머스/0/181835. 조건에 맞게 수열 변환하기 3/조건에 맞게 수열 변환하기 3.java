class Solution {
    public int[] solution(int[] arr, int k) {
        // k가 홀수이면 각 원소에 k를 곱하고, 짝수이면 k를 더함
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (k % 2 == 1) ? arr[i] * k : arr[i] + k;
        }
        return arr;
    }
}
