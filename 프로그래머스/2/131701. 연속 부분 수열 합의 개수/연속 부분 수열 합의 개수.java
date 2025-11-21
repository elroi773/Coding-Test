import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int n = elements.length;

        // 원형 처리를 위해 배열 2배로 확장
        int[] arr = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            arr[i] = elements[i % n];
        }

        // prefix sum 배열 생성
        int[] prefix = new int[2 * n + 1];
        for (int i = 0; i < 2 * n; i++) {
            prefix[i + 1] = prefix[i] + arr[i];
        }

        // 가능한 부분합을 중복 없이 저장할 Set
        HashSet<Integer> set = new HashSet<>();

        // 길이 1부터 n까지 부분합 계산
        for (int length = 1; length <= n; length++) {
            for (int start = 0; start < n; start++) {
                int sum = prefix[start + length] - prefix[start];
                set.add(sum);
            }
        }

        return set.size();
    }
}
