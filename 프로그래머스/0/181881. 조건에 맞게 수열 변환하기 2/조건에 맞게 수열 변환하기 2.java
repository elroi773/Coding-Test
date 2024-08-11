class Solution {
    public int solution(int[] arr) {
        int x = 0;
        
        while (true) {
            x++;
            int[] prevArr = arr.clone();  // 현재 상태 복사
            
            // 주어진 규칙에 따라 배열 수정
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] >= 50 && arr[i] % 2 == 0) {
                    arr[i] /= 2;
                } else if (arr[i] < 50 && arr[i] % 2 == 1) {
                    arr[i] = arr[i] * 2 + 1;
                }
            }
            
            // 이전 상태와 현재 상태가 같다면 종료
            if (java.util.Arrays.equals(arr, prevArr)) {
                break;
            }
        }
        
        return x - 1;  
    }
}
