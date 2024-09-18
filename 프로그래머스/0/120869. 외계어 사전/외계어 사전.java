import java.util.Arrays;

class Solution {
    public int solution(String[] spell, String[] dic) {
        // spell 배열을 정렬한 문자열로 변환
        String sortedSpell = String.join("", spell);
        char[] sortedSpellArr = sortedSpell.toCharArray();
        Arrays.sort(sortedSpellArr);
        sortedSpell = new String(sortedSpellArr);

        // dic 배열을 순회하며 각 단어를 정렬해서 비교
        for (String word : dic) {
            char[] wordArr = word.toCharArray();
            Arrays.sort(wordArr);
            String sortedWord = new String(wordArr);

            if (sortedSpell.equals(sortedWord)) {
                return 1; // spell을 모두 사용한 단어가 dic에 있으면 1 반환
            }
        }

        return 2; // 일치하는 단어가 없으면 2 반환
    }
}
