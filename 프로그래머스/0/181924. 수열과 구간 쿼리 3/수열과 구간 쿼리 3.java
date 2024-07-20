class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        // queries 배열을 순회하면서 각 쿼리를 처리
        for (int[] query : queries) {
            int i = query[0];
            int j = query[1];
            
            // arr[i]와 arr[j]의 값을 서로 교환
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        
        // 최종적으로 변환된 배열 반환
        return arr;
    }
}
