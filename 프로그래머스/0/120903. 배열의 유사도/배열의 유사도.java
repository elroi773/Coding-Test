import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(String[] s1, String[] s2) {
        // 두 배열의 교집합을 계산하기 위해 Set을 사용
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        
        // s1과 s2의 원소를 각각 set에 넣습니다.
        for (String s : s1) {
            set1.add(s);
        }
        for (String s : s2) {
            set2.add(s);
        }
        
        // set1과 set2의 교집합 원소 개수를 계산합니다.
        set1.retainAll(set2);
        
        // 교집합의 크기가 같은 원소의 개수입니다.
        return set1.size();
    }
}
