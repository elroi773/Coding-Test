import java.util.*;

class Solution {

    // 소수 판별 함수
    public boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // DFS로 모든 숫자 조합 만들기
    public void dfs(String numbers, boolean[] visited, String current, Set<Integer> set) {
        if (!current.equals("")) {
            set.add(Integer.parseInt(current));
        }

        for (int i = 0; i < numbers.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(numbers, visited, current + numbers.charAt(i), set);
                visited[i] = false;
            }
        }
    }

    public int solution(String numbers) {
        Set<Integer> set = new HashSet<>();
        boolean[] visited = new boolean[numbers.length()];

        // 모든 조합 생성
        dfs(numbers, visited, "", set);

        // 소수 개수 세기
        int count = 0;
        for (int num : set) {
            if (isPrime(num)) count++;
        }

        return count;
    }
}
