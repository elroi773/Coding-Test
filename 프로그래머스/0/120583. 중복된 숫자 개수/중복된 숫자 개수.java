class Solution {
    public int solution(int[] array, int n) {
        int count = 0;
        
        // 배열을 순회하면서 n과 같은 값이 있는지 확인합니다.
        for (int num : array) {
            if (num == n) {
                count++;
            }
        }
        
        return count;  // n의 개수를 반환합니다.
    }
}
