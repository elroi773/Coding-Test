import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int[] arr) {
        List<Integer> tempList = new ArrayList<>();

        for (int a : arr) {
            for (int i = 0; i < a; i++) {
                tempList.add(a);
            }
        }

        // Convert the ArrayList to an int array
        int[] answer = new int[tempList.size()];
        for (int i = 0; i < tempList.size(); i++) {
            answer[i] = tempList.get(i);
        }

        return answer;
    }
}
