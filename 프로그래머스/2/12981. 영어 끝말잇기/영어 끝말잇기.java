import java.util.HashSet;
import java.util.Set;

class Solution {
    public int[] solution(int n, String[] words) {
        Set<String> used = new HashSet<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            // 1️⃣ 한 글자인 단어
            if (word.length() < 2) {
                return new int[]{(i % n) + 1, (i / n) + 1};
            }

            // 2️⃣ 이미 나온 단어
            if (used.contains(word)) {
                return new int[]{(i % n) + 1, (i / n) + 1};
            }

            // 3️⃣ 끝말잇기 규칙 위반 (첫 단어 제외)
            if (i > 0) {
                String prev = words[i - 1];
                if (prev.charAt(prev.length() - 1) != word.charAt(0)) {
                    return new int[]{(i % n) + 1, (i / n) + 1};
                }
            }

            used.add(word);
        }

        // 탈락자가 없는 경우
        return new int[]{0, 0};
    }
}
