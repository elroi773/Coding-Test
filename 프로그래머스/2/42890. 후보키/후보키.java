import java.util.*;

class Solution {
    public int solution(String[][] relation) {
        int rowLen = relation.length;
        int colLen = relation[0].length;

        List<Integer> candidateKeys = new ArrayList<>();

        // 1) 컬럼 조합을 비트마스크로 탐색
        for (int bit = 1; bit < (1 << colLen); bit++) {

            // 2) 유일성 검사
            Set<String> set = new HashSet<>();

            for (int r = 0; r < rowLen; r++) {
                StringBuilder sb = new StringBuilder();
                for (int c = 0; c < colLen; c++) {
                    if ((bit & (1 << c)) != 0) {  // 해당 column 사용
                        sb.append(relation[r][c]).append(",");
                    }
                }
                set.add(sb.toString());
            }

            // 유일성 실패
            if (set.size() != rowLen) continue;

            // 3) 최소성 검사
            boolean minimal = true;
            for (int key : candidateKeys) {
                // key가 bit의 부분집합이면 최소성 깨짐
                if ((key & bit) == key) {
                    minimal = false;
                    break;
                }
            }

            // 최소성 OK → 후보키로 등록
            if (minimal) {
                candidateKeys.add(bit);
            }
        }

        return candidateKeys.size();
    }
}
