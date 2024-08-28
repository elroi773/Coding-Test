class Solution {
    public int solution(int[] array, int height) {
        int count = 0;
        
        // 배열을 순회하면서 머쓱이보다 큰 키를 가진 사람을 셉니다.
        for (int h : array) {
            if (h > height) {
                count++;
            }
        }
        
        return count;  // 키가 더 큰 사람의 수를 반환합니다.
    }
}
