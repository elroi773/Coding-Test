import java.util.Arrays;

class Solution {
    public int[] solution(int[] num_list, int n) {
        // n-1부터 끝까지의 배열을 반환
        return Arrays.copyOfRange(num_list, n - 1, num_list.length);
    }
}
