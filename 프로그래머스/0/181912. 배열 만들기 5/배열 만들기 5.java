import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(String[] intStrs, int k, int s, int l) {
        List<Integer> resultList = new ArrayList<>();

        // Iterate through each string in the array
        for (String str : intStrs) {
            // Extract the substring starting at index s with length l
            String subStr = str.substring(s, s + l);
            
            // Convert the substring to an integer
            int num = Integer.parseInt(subStr);
            
            // Check if the integer is greater than k
            if (num > k) {
                // Add the integer to the result list
                resultList.add(num);
            }
        }

        // Convert the result list to an array
        int[] resultArray = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            resultArray[i] = resultList.get(i);
        }

        return resultArray;
    }
}
