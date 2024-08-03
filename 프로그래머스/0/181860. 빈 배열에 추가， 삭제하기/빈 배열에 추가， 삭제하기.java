import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int[] arr, boolean[] flag) {
        List<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < flag.length; i++) {
            if (flag[i]) {
                // Add arr[i] to the list arr[i] * 2 times
                for (int j = 0; j < arr[i] * 2; j++) {
                    list.add(arr[i]);
                }
            } else {
                // Remove the last arr[i] elements from the list
                for (int j = 0; j < arr[i]; j++) {
                    if (!list.isEmpty()) {  // Ensure we don't remove more than exists
                        list.remove(list.size() - 1);
                    }
                }
            }
        }
        
        // Convert the list to an array
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}
