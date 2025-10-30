class Solution {
    // 최대공약수(GCD) 구하는 함수
    private int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    // 배열 전체의 GCD 구하기
    private int getGCD(int[] arr) {
        int result = arr[0];
        for (int i = 1; i < arr.length; i++) {
            result = gcd(result, arr[i]);
        }
        return result;
    }

    // arr의 모든 원소가 x로 나누어지지 않는지 검사
    private boolean notDivisible(int[] arr, int x) {
        for (int n : arr) {
            if (n % x == 0) return false;
        }
        return true;
    }

    public int solution(int[] arrayA, int[] arrayB) {
        int gcdA = getGCD(arrayA);
        int gcdB = getGCD(arrayB);
        int candidateA = 0;
        int candidateB = 0;

        // 조건 1: A의 모든 원소를 나누지만, B의 어떤 원소도 나누지 않음
        if (notDivisible(arrayB, gcdA)) candidateA = gcdA;

        // 조건 2: B의 모든 원소를 나누지만, A의 어떤 원소도 나누지 않음
        if (notDivisible(arrayA, gcdB)) candidateB = gcdB;

        return Math.max(candidateA, candidateB);
    }
}
