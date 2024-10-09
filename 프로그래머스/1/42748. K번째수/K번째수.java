import java.util.Arrays;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];  // 결과를 담을 배열 선언
        
        for (int index = 0; index < commands.length; index++) {
            int i = commands[index][0];  // 시작 인덱스
            int j = commands[index][1];  // 끝 인덱스
            int k = commands[index][2];  // 정렬 후 원하는 인덱스
            
            // 배열 자르기 (i-1부터 j까지)
            int[] temp = Arrays.copyOfRange(array, i - 1, j);
            
            // 자른 배열 정렬
            Arrays.sort(temp);
            
            // 정렬된 배열에서 k번째 숫자 찾기 (k-1 인덱스)
            answer[index] = temp[k - 1];
        }
        
        return answer;
    }
}
