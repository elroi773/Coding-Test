class Solution {
    public String solution(int[] food) {
        StringBuilder leftSide = new StringBuilder();
        
        // 각 음식의 절반을 왼쪽에서 먹을 수 있도록 배치
        for (int i = 1; i < food.length; i++) {
            int count = food[i] / 2;  // 각 음식의 절반을 가져옴
            for (int j = 0; j < count; j++) {
                leftSide.append(i);  // 왼쪽에서 먹는 음식을 추가
            }
        }
        
        // 왼쪽 음식 + 물(0) + 오른쪽 음식을 만들어 반환
        String rightSide = leftSide.reverse().toString();  // 왼쪽 음식을 뒤집어서 오른쪽에 배치
        return leftSide.reverse().toString() + "0" + rightSide;
    }
}
