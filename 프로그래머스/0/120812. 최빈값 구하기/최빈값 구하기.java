import java.util.HashMap;

class Solution {
    public int solution(int[] array) {
        // 숫자 등장 횟수를 저장할 HashMap 생성
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        
        // 배열의 각 원소에 대해 등장 횟수 카운팅
        for (int num : array) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // 최빈값과 그 빈도 수를 저장할 변수 초기화
        int maxFreq = 0;
        int mode = -1;
        boolean isDuplicate = false;
        
        // HashMap을 순회하며 최빈값 찾기
        for (int key : frequencyMap.keySet()) {
            int freq = frequencyMap.get(key);
            if (freq > maxFreq) {
                maxFreq = freq;
                mode = key;
                isDuplicate = false;
            } else if (freq == maxFreq) {
                isDuplicate = true;
            }
        }
        
        // 최빈값이 여러 개라면 -1 반환
        return isDuplicate ? -1 : mode;
    }
}
