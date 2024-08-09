import java.util.HashSet;
import java.util.Set;

class Solution {
    public int[] solution(int[] arr, int k) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        int[] answer = new int[k];
        int index = 0;

       
        for (int num : arr) {
            if (uniqueNumbers.add(num)) {  
                answer[index++] = num;
                if (index == k) break;  
            }
        }

        while (index < k) {
            answer[index++] = -1;
        }

        return answer;
    }
}
