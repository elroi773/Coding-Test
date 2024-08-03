import java.util.ArrayList;
import java.util.List;

class Solution {
    public String[] solution(String[] strArr) {
        List<String> filteredList = new ArrayList<>();
        
        for (String str : strArr) {
            if (!str.contains("ad")) {
                filteredList.add(str);
            }
        }
        
        // Convert the list to an array
        String[] answer = new String[filteredList.size()];
        return filteredList.toArray(answer);
    }
}
