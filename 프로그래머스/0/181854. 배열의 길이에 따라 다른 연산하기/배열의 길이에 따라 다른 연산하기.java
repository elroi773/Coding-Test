class Solution {
    public int[] solution(int[] arr, int n) {
        // 배열의 길이에 따라 작업을 다르게 수행
        int length = arr.length;
        int[] answer = new int[length];
        
        // 배열을 순회하며 조건에 맞게 값 수정
        for (int i = 0; i < length; i++) {
            if (length % 2 == 1) { // 길이가 홀수인 경우
                if (i % 2 == 0) { // 짝수 인덱스
                    answer[i] = arr[i] + n;
                } else { // 홀수 인덱스
                    answer[i] = arr[i];
                }
            } else { // 길이가 짝수인 경우
                if (i % 2 == 1) { // 홀수 인덱스
                    answer[i] = arr[i] + n;
                } else { // 짝수 인덱스
                    answer[i] = arr[i];
                }
            }
        }
        
        return answer;
    }
}
