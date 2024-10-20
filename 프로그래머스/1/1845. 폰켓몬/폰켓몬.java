import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int[] nums) {
        // 중복을 제거한 폰켓몬 종류를 저장할 Set
        Set<Integer> uniqueTypes = new HashSet<>();
        
        // nums 배열의 각 폰켓몬 종류 번호를 Set에 추가 (중복 자동 제거)
        for (int num : nums) {
            uniqueTypes.add(num);
        }
        
        // 선택할 수 있는 폰켓몬의 최대 마리 수는 N/2
        int maxSelectable = nums.length / 2;
        
        // 고를 수 있는 종류의 최대값은 폰켓몬 종류 수와 maxSelectable 중 작은 값
        return Math.min(uniqueTypes.size(), maxSelectable);
    }
}
