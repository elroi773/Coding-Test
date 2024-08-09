import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
    public int solution(int[] rank, boolean[] attendance) {
        // List to store the eligible students' indices
        List<Integer> eligibleStudents = new ArrayList<>();
        
        // Collect indices of students who can attend
        for (int i = 0; i < rank.length; i++) {
            if (attendance[i]) {
                eligibleStudents.add(i);
            }
        }
        
        eligibleStudents.sort(Comparator.comparingInt(i -> rank[i]));
        
        int a = eligibleStudents.get(0);
        int b = eligibleStudents.get(1);
        int c = eligibleStudents.get(2);
        
        
        return 10000 * a + 100 * b + c;
    }
}
