import java.util.Arrays;

class Solution {
    public int solution(int[] sides) {
        // 배열을 오름차순 정렬
        Arrays.sort(sides);
        
        // 가장 긴 변은 배열의 마지막 요소
        // 나머지 두 변의 합이 가장 긴 변보다 큰지 확인
        if (sides[0] + sides[1] > sides[2]) {
            return 1; // 삼각형을 만들 수 있음
        } else {
            return 2; // 삼각형을 만들 수 없음
        }
    }
}
