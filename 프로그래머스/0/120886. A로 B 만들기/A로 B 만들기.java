import java.util.Arrays;

class Solution {
    public int solution(String before, String after) {
        // 문자열을 각각 문자 배열로 변환한 뒤 정렬
        char[] beforeArr = before.toCharArray();
        char[] afterArr = after.toCharArray();
        Arrays.sort(beforeArr);
        Arrays.sort(afterArr);
        
        // 정렬된 배열이 같은지 비교
        if (Arrays.equals(beforeArr, afterArr)) {
            return 1; // 같으면 1 반환 (애너그램 관계)
        } else {
            return 0; // 다르면 0 반환
        }
    }
}
