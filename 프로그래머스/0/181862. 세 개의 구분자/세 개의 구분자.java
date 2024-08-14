import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public static List<String> solution(String myStr) {
        // "a", "b", "c"를 구분자로 사용하여 문자열을 나누고 빈 문자열 제거
        List<String> result = Arrays.stream(myStr.split("[abc]"))
                                    .filter(s -> !s.isEmpty())
                                    .collect(Collectors.toList());
        
        // 결과 리스트가 비어있으면 "EMPTY" 추가
        if (result.isEmpty()) {
            return List.of("EMPTY");
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        // 테스트
        System.out.println(solution("baconlettucetomato"));  // [onlettu, etom, to]
        System.out.println(solution("abcabcabc"));           // [EMPTY]
        System.out.println(solution("abacabadabacaba"));     // [d]
        System.out.println(solution("xyz"));                 // [xyz]
    }
}
