import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(int a, int b, int c, int d) {
        int[] dice = {a, b, c, d};
        Map<Integer, Integer> freqMap = new HashMap<>();
        
        // 주사위 숫자의 빈도 계산
        for (int num : dice) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        // 경우에 따른 점수 계산
        if (freqMap.size() == 1) {
            // 네 주사위에서 나온 숫자가 모두 같음
            int p = a;
            return 1111 * p;
        } else if (freqMap.size() == 2) {
            // 두 숫자가 각각 3개, 1개 또는 2개씩 나오는 경우
            int p = 0, q = 0;
            for (int key : freqMap.keySet()) {
                if (freqMap.get(key) == 3) {
                    p = key;
                } else if (freqMap.get(key) == 1) {
                    q = key;
                } else if (freqMap.get(key) == 2) {
                    if (p == 0) {
                        p = key;
                    } else {
                        q = key;
                    }
                }
            }
            if (freqMap.containsValue(3)) {
                return (10 * p + q) * (10 * p + q);
            } else {
                return (p + q) * Math.abs(p - q);
            }
        } else if (freqMap.size() == 3) {
            // 두 주사위가 같은 숫자이고 나머지 두 주사위는 각각 다른 숫자인 경우
            int p = 0, q = 0, r = 0;
            for (int key : freqMap.keySet()) {
                if (freqMap.get(key) == 2) {
                    p = key;
                } else if (freqMap.get(key) == 1) {
                    if (q == 0) {
                        q = key;
                    } else {
                        r = key;
                    }
                }
            }
            return q * r;
        } else {
            // 네 주사위에 적힌 숫자가 모두 다른 경우
            int min = Integer.MAX_VALUE;
            for (int num : dice) {
                if (num < min) {
                    min = num;
                }
            }
            return min;
        }
    }
}
