import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public String[] solution(String[] strings, int n) {
        // 각 문자열의 n번째 문자로 정렬하고, 같은 경우 사전순으로 정렬
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                // n번째 문자로 비교
                int cmp = Character.compare(a.charAt(n), b.charAt(n));
                // n번째 문자가 같으면 사전순으로 비교
                return cmp != 0 ? cmp : a.compareTo(b);
            }
        });
        
        return strings; // 정렬된 배열 반환
    }
}
