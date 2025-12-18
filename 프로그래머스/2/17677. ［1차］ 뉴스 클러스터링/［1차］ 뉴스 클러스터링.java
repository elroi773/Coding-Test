import java.util.*;

class Solution {
    public int solution(String str1, String str2) {

        Map<String, Integer> map1 = makeMap(str1);
        Map<String, Integer> map2 = makeMap(str2);

        // 둘 다 공집합이면 유사도 1
        if (map1.isEmpty() && map2.isEmpty()) {
            return 65536;
        }

        int intersection = 0;
        int union = 0;

        Set<String> keys = new HashSet<>();
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());

        for (String key : keys) {
            int cnt1 = map1.getOrDefault(key, 0);
            int cnt2 = map2.getOrDefault(key, 0);

            intersection += Math.min(cnt1, cnt2);
            union += Math.max(cnt1, cnt2);
        }

        return (int) ((double) intersection / union * 65536);
    }

    private Map<String, Integer> makeMap(String str) {
        Map<String, Integer> map = new HashMap<>();
        str = str.toLowerCase();

        for (int i = 0; i < str.length() - 1; i++) {
            char a = str.charAt(i);
            char b = str.charAt(i + 1);

            if (Character.isLetter(a) && Character.isLetter(b)) {
                String key = "" + a + b;
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }

        return map;
    }
}
