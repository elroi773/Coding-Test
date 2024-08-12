import java.util.Arrays;

class Solution {
    public int solution(int[] array) {
        // 배열을 오름차순으로 정렬합니다.
        Arrays.sort(array);
        
        // 중앙값을 찾기 위해 배열의 가운데 인덱스를 계산합니다.
        int middleIndex = array.length / 2;
        
        // 중앙값을 반환합니다.
        return array[middleIndex];
    }
}
