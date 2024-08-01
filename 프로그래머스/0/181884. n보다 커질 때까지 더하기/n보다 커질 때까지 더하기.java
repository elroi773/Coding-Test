class Solution {
    public int solution(int[] numbers, int n) {
        int sum = 0; // 원소들의 합을 저장할 변수

        // numbers 배열의 원소를 순차적으로 더합니다.
        for (int number : numbers) {
            sum += number; // 현재 원소를 합에 추가합니다.

            // 합이 n보다 커지는 경우 반복문을 종료합니다.
            if (sum > n) {
                break;
            }
        }

        // 최종적으로 계산된 합을 반환합니다.
        return sum;
    }
}
