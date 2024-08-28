class Solution {
    public int[] solution(int[] array) {
        // 가장 큰 수와 인덱스를 저장할 변수 초기화
        int maxNumber = array[0];
        int maxIndex = 0;
        
        // 배열을 순회하며 가장 큰 수와 인덱스를 찾음
        for (int i = 1; i < array.length; i++) {
            if (array[i] > maxNumber) {
                maxNumber = array[i];
                maxIndex = i;
            }
        }
        
        // 결과 배열에 가장 큰 수와 그 인덱스를 담아 반환
        return new int[] {maxNumber, maxIndex};
    }
}
