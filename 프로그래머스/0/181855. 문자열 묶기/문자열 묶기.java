import java.util.HashMap;

class Solution {
    public int solution(String[] strArr) {
        HashMap<Integer, Integer> lengthCountMap = new HashMap<>();
        
        // 각 문자열의 길이를 계산하고, 해당 길이에 따른 개수를 세는 작업
        for (String str : strArr) {
            int length = str.length();
            lengthCountMap.put(length, lengthCountMap.getOrDefault(length, 0) + 1);
        }
        
        // 가장 큰 그룹의 크기를 찾기
        int maxCount = 0;
        for (int count : lengthCountMap.values()) {
            if (count > maxCount) {
                maxCount = count;
            }
        }
        
        return maxCount;
    }
}
