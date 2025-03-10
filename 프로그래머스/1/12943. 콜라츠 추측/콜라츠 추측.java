class Solution {
    public int solution(int num) {
        int answer = 0;
        long n = num; // long 타입으로 변환하여 오버플로우 방지

        while (n != 1) {
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n = n * 3 + 1;
            }
            answer++;

            if (answer >= 500) {
                return -1;
            }
        }

        return answer;
    }
}
