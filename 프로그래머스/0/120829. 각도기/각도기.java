class Solution {
    public int solution(int angle) {
        if (angle > 0 && angle < 90) {
            return 1; // 예각
        } else if (angle == 90) {
            return 2; // 직각
        } else if (angle > 90 && angle < 180) {
            return 3; // 둔각
        } else if (angle == 180) {
            return 4; // 평각
        }
        return 0; // 이 조건은 실제로는 발생하지 않음 (제한사항에 의해)
    }
}
