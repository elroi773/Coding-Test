import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public String[] solution(String myString) {
        String[] parts = myString.split("x");
        
        ArrayList<String> nonEmptyParts = new ArrayList<>();
        for (String part : parts) {
            if (!part.isEmpty()) {
                nonEmptyParts.add(part);
            }
        }
        
        String[] answer = nonEmptyParts.toArray(new String[0]);
        Arrays.sort(answer);
        
        return answer;
    }
}
