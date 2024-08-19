class Solution {
    public int solution(int[] common) {
        int n = common.length;
        
        // 등차수열인지 확인
        if (common[1] - common[0] == common[2] - common[1]) {
            int diff = common[1] - common[0];
            return common[n - 1] + diff;
        } 
        // 등비수열인 경우
        else {
            int ratio = common[1] / common[0];
            return common[n - 1] * ratio;
        }
    }
}
