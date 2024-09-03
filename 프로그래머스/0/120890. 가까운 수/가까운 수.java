class Solution {
    public int solution(int[] array, int n) {
        int answer = array[0];  // 초기값을 배열의 첫 번째 값으로 설정합니다.
        int minDiff = Math.abs(array[0] - n);  // 초기 최소 거리를 계산합니다.

        for (int i = 1; i < array.length; i++) {
            int currentDiff = Math.abs(array[i] - n);  // 현재 요소와 n의 거리를 계산합니다.

            if (currentDiff < minDiff) {
                // 더 작은 거리를 찾은 경우
                minDiff = currentDiff;
                answer = array[i];
            } else if (currentDiff == minDiff && array[i] < answer) {
                // 같은 거리를 가진 경우 더 작은 값을 선택합니다.
                answer = array[i];
            }
        }

        return answer;
    }
}
