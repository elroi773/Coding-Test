import java.util.ArrayList;

class Solution {
    public int[] solution(int n, int[] slicer, int[] num_list) {
        // Extract a, b, c from the slicer array
        int a = slicer[0];
        int b = slicer[1];
        int c = slicer[2];
        
        // Initialize the result list
        ArrayList<Integer> resultList = new ArrayList<>();
        
        // Perform slicing based on the value of n
        if (n == 1) {
            // Slice from index 0 to b
            for (int i = 0; i <= b && i < num_list.length; i++) {
                resultList.add(num_list[i]);
            }
        } else if (n == 2) {
            // Slice from index a to the end of the list
            for (int i = a; i < num_list.length; i++) {
                resultList.add(num_list[i]);
            }
        } else if (n == 3) {
            // Slice from index a to b
            for (int i = a; i <= b && i < num_list.length; i++) {
                resultList.add(num_list[i]);
            }
        } else if (n == 4) {
            // Slice from index a to b with step size c
            for (int i = a; i <= b && i < num_list.length; i += c) {
                resultList.add(num_list[i]);
            }
        }
        
        // Convert the ArrayList to an array and return it
        int[] answer = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            answer[i] = resultList.get(i);
        }
        
        return answer;
    }
}
