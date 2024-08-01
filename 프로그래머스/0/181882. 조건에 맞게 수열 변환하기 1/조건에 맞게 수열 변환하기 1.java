class Solution {
    public int[] solution(int[] arr) {
        // 주어진 배열의 크기만큼의 배열을 생성합니다.
        int[] answer = new int[arr.length];
        
        // 주어진 배열을 순회하면서 각 요소에 대해 조건을 적용합니다.
        for (int i = 0; i < arr.length; i++) {
            // 현재 요소가 50보다 크거나 같고 짝수인 경우
            if (arr[i] >= 50 && arr[i] % 2 == 0) {
                answer[i] = arr[i] / 2; // 2로 나눕니다.
            }
            // 현재 요소가 50보다 작고 홀수인 경우
            else if (arr[i] < 50 && arr[i] % 2 != 0) {
                answer[i] = arr[i] * 2; // 2를 곱합니다.
            }
            // 위의 두 조건에 해당하지 않는 경우는 원래 값을 그대로 사용합니다.
            else {
                answer[i] = arr[i];
            }
        }
        
        // 결과 배열을 반환합니다.
        return answer;
    }
}
