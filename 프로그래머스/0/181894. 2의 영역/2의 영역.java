class Solution {
    public int[] solution(int[] arr) {
        // 초기화: 첫 번째와 마지막 '2'의 인덱스를 찾기 위한 변수
        int firstIndex = -1;
        int lastIndex = -1;
        
        // 배열을 순회하여 첫 번째와 마지막 '2'의 위치를 기록
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 2) {
                if (firstIndex == -1) {
                    firstIndex = i; // 첫 번째 '2'의 위치 기록
                }
                lastIndex = i; // 마지막 '2'의 위치 갱신
            }
        }
        
        // 만약 '2'가 없다면 [-1]을 반환
        if (firstIndex == -1) {
            return new int[] {-1};
        }
        
        // 첫 번째 '2'부터 마지막 '2'까지의 부분 배열을 반환
        int[] answer = new int[lastIndex - firstIndex + 1];
        for (int i = firstIndex; i <= lastIndex; i++) {
            answer[i - firstIndex] = arr[i];
        }
        
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // 테스트 예제 1
        int[] arr1 = {1, 2, 3, 4, 2, 5, 2};
        int[] result1 = sol.solution(arr1);
        System.out.println(java.util.Arrays.toString(result1)); // 출력: [2, 3, 4, 2, 5, 2]

        // 테스트 예제 2
        int[] arr2 = {1, 3, 4, 5};
        int[] result2 = sol.solution(arr2);
        System.out.println(java.util.Arrays.toString(result2)); // 출력: [-1]

        // 테스트 예제 3
        int[] arr3 = {2, 1, 2};
        int[] result3 = sol.solution(arr3);
        System.out.println(java.util.Arrays.toString(result3)); // 출력: [2, 1, 2]
    }
}
