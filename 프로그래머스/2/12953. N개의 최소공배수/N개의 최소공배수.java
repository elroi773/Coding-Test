class Solution {
    public int solution(int[] arr) {
        int lcm = arr[0];

        for (int i = 1; i < arr.length; i++) {
            lcm = lcm * arr[i] / gcd(lcm, arr[i]);
        }

        return lcm;
    }

    // 최대공약수 (유클리드 호제법)
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}
