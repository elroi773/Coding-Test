class Solution {
    public int solution(int a, int d, boolean[] included) {
        int answer = 0;
        
        // included 배열을 순회하면서 true인 경우 해당 항을 더한다
        for (int i = 0; i < included.length; i++) {
            if (included[i]) {
                answer += a + (i * d); // i + 1번째 항은 a + i * d
            }
        }
        
        return answer;
    }
}
