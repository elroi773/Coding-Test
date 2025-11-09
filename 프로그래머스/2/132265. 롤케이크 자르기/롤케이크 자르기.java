import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int n = topping.length;
        int answer = 0;

        int[] left = new int[n];
        int[] right = new int[n];

        Set<Integer> leftSet = new HashSet<>();
        Set<Integer> rightSet = new HashSet<>();

        // 1️⃣ 왼쪽 누적
        for (int i = 0; i < n; i++) {
            leftSet.add(topping[i]);
            left[i] = leftSet.size();
        }

        // 2️⃣ 오른쪽 누적
        for (int i = n - 1; i >= 0; i--) {
            rightSet.add(topping[i]);
            right[i] = rightSet.size();
        }

        // 3️⃣ 비교
        for (int i = 0; i < n - 1; i++) {
            if (left[i] == right[i + 1]) {
                answer++;
            }
        }

        return answer;
    }
}
