class Solution {
    public int solution(int n) {
        final int MOD = 1234567;

        int a = 0; // F(0)
        int b = 1; // F(1)

        for (int i = 2; i <= n; i++) {
            int next = (a + b) % MOD;
            a = b;
            b = next;
        }

        return b;
    }
}
