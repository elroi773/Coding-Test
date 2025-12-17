import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        // cacheSize가 0이면 항상 miss
        if (cacheSize == 0) return cities.length * 5;

        int time = 0;

        // accessOrder=true -> 접근(조회/갱신)할 때마다 순서가 최신으로 바뀜 (LRU 구현)
        LinkedHashMap<String, Boolean> cache = new LinkedHashMap<>(cacheSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Boolean> eldest) {
                return size() > cacheSize; // 용량 초과 시 가장 오래된 항목 제거
            }
        };

        for (String city : cities) {
            String key = city.toLowerCase(Locale.ROOT);

            if (cache.containsKey(key)) {  // hit
                time += 1;
                cache.get(key);            // accessOrder 갱신용(사실 put만 해도 됨)
            } else {                       // miss
                time += 5;
                cache.put(key, true);      // put 시 용량 초과면 removeEldestEntry로 자동 제거
            }
        }

        return time;
    }
}