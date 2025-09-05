function solution(nums) {
    // 중복 제거: Set 사용
    const kinds = new Set(nums);
    
    // 가져갈 수 있는 수
    const maxPick = nums.length / 2;
    
    // 최대 종류 수 = min(서로 다른 종류 수, 가져갈 수 있는 수)
    return Math.min(kinds.size, maxPick);
}
