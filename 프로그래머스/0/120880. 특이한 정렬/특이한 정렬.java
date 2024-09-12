import java.util.Arrays;

class Solution {
    public int[] solution(int[] numlist, int n) {
        // 배열을 Integer 배열로 변환
        Integer[] arr = Arrays.stream(numlist).boxed().toArray(Integer[]::new);
        
        // 정렬 기준 정의
        Arrays.sort(arr, (a, b) -> {
            int diffA = Math.abs(a - n); // a와 n의 차이
            int diffB = Math.abs(b - n); // b와 n의 차이
            
            if (diffA == diffB) {
                // 차이가 같으면 더 큰 수를 우선으로
                return b - a;
            } else {
                // 차이가 다르면 차이가 작은 순으로
                return diffA - diffB;
            }
        });
        
        // 다시 int[]로 변환하여 반환
        return Arrays.stream(arr).mapToInt(Integer::intValue).toArray();
    }
}
