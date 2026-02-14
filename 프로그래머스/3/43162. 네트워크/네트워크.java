class Solution {
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        int answer = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                answer++;
                dfs(i, computers, visited, n);
            }
        }

        return answer;
    }

    private void dfs(int cur, int[][] computers, boolean[] visited, int n) {
        visited[cur] = true;

        for (int next = 0; next < n; next++) {
            if (computers[cur][next] == 1 && !visited[next]) {
                dfs(next, computers, visited, n);
            }
        }
    }
}