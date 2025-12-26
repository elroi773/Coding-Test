class Solution {
    public int solution(int n, int[] tops) {
        final int MOD = 10007;

        int free = 1; // 현재 정점이 아직 매칭되지 않아 오른쪽과 붙을 수 있는 상태
        int occ  = 0; // 현재 정점이 이미 왼쪽/위와 붙어 점유된 상태

        // 경로 정점은 총 2n+1개: v1..v(2n+1)
        // v1은 단독만 가능하므로 초기값 free=1, occ=0
        for (int j = 2; j <= 2 * n + 1; j++) {
            int hasTop = 0;
            if ((j & 1) == 0) { // 짝수 정점이 tops에 대응
                int idx = j / 2 - 1; // 0-based
                hasTop = tops[idx];
            }

            int newFree = (free + occ) % MOD; // 단독 배치
            int newOcc  = free % MOD;         // 왼쪽과 마름모로 연결(경로 간선)

            if (hasTop == 1) {
                newOcc = (newOcc + free + occ) % MOD; // 위 가지와 마름모로 연결
            }

            free = newFree;
            occ  = newOcc;
        }

        return (free + occ) % MOD;
    }
}