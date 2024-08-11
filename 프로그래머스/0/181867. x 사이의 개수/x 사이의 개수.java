class Solution {
    public int[] solution(String myString) {
        int n = myString.length();
        int[] lengths = new int[n + 1];  // 최대 n+1개의 부분 문자열이 있을 수 있음
        int index = 0, length = 0;

        // 문자열을 한 문자씩 탐색하면서 처리
        for (int i = 0; i < n; i++) {
            if (myString.charAt(i) == 'x') {
                lengths[index++] = length;
                length = 0;  // 길이 초기화
            } else {
                length++;
            }
        }
        lengths[index++] = length;  // 마지막 부분 문자열의 길이 저장

        // 결과 배열 크기 조정
        int[] result = new int[index];
        System.arraycopy(lengths, 0, result, 0, index);
        
        return result;
    }
}
