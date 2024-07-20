class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        // queries 배열을 순회하면서 각 쿼리를 처리
        for (int[] query : queries) {
            int s = query[0];
            int e = query[1];
            int k = query[2];
            
            // s부터 e까지 순회하면서 k의 배수인 인덱스를 찾고 arr[i]를 1 증가
            for (int i = s; i <= e; i++) {
                if (i % k == 0) {
                    arr[i]++;
                }
            }
        }
        
        // 최종적으로 변환된 배열 반환
        return arr;
    }
}
