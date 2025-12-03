class Solution {
    public int solution(int n, int k) {
        // 1. n을 k진수 문자열로 변환
        String baseK = toBaseK(n, k);

        int count = 0;

        // 2. '0'을 기준으로 나누기 (연속된 0도 한 번에 처리)
        String[] parts = baseK.split("0+");

        for (String part : parts) {
            if (part.isEmpty()) continue; // 빈 문자열은 스킵

            long num = Long.parseLong(part); // 10진수로 해석
            if (isPrime(num)) {
                count++;
            }
        }

        return count;
    }

    // n을 k진수 문자열로 변환
    private String toBaseK(int n, int k) {
        StringBuilder sb = new StringBuilder();
        long num = n;

        if (num == 0) return "0";

        while (num > 0) {
            sb.append(num % k);    // k는 3~10이므로 한 자리 숫자(0~9)
            num /= k;
        }
        return sb.reverse().toString();
    }

    // 소수 판별 (long 사용)
    private boolean isPrime(long x) {
        if (x < 2) return false;
        if (x == 2) return true;
        if (x % 2 == 0) return false;

        for (long i = 3; i * i <= x; i += 2) {
            if (x % i == 0) return false;
        }
        return true;
    }
}