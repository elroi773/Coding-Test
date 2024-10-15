import java.util.*;

class Solution {
    public long solution(long n) {
        // 1. 정수 n을 문자열로 변환
        String str = Long.toString(n);
        
        // 2. 문자열을 문자 배열로 변환
        char[] chars = str.toCharArray();
        
        // 3. 문자 배열을 내림차순으로 정렬
        Arrays.sort(chars);
        
        // 4. 내림차순으로 정렬하기 위해 배열을 뒤집음
        StringBuilder sb = new StringBuilder(new String(chars));
        sb.reverse();
        
        // 5. 다시 문자열을 long 타입으로 변환하여 반환
        return Long.parseLong(sb.toString());
    }
}
