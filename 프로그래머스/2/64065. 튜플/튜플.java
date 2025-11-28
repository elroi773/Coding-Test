import java.util.*;

class Solution {
    public int[] solution(String s) {
        // 1. 바깥 {{ }} 제거
        String trimmed = s.substring(2, s.length() - 2);

        // 2. "},{" 기준으로 나누기
        String[] parts = trimmed.split("\\},\\{");

        // 3. 각 부분을 숫자 리스트로 변환
        List<List<Integer>> list = new ArrayList<>();
        for (String part : parts) {
            String[] nums = part.split(",");
            List<Integer> temp = new ArrayList<>();
            for (String num : nums) {
                temp.add(Integer.parseInt(num));
            }
            list.add(temp);
        }

        // 4. 원소 개수 기준 오름차순 정렬
        list.sort(Comparator.comparingInt(List::size));

        List<Integer> answerList = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();

        // 5. 작은 집합부터 새로운 숫자만 추가
        for (List<Integer> subset : list) {
            for (int num : subset) {
                if (!seen.contains(num)) {
                    seen.add(num);
                    answerList.add(num);
                }
            }
        }

        // 6. List → int[]
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}
