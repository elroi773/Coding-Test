class Solution {
    public int[] solution(int[] arr, int[][] intervals) {
        // 첫 번째 구간
        int a1 = intervals[0][0];
        int b1 = intervals[0][1];
        // 두 번째 구간
        int a2 = intervals[1][0];
        int b2 = intervals[1][1];
        
        // 구간 추출
        int[] part1 = new int[b1 - a1 + 1];
        int[] part2 = new int[b2 - a2 + 1];
        
        for (int i = a1; i <= b1; i++) {
            part1[i - a1] = arr[i];
        }
        
        for (int i = a2; i <= b2; i++) {
            part2[i - a2] = arr[i];
        }
        
        // 새로운 배열 생성
        int[] answer = new int[part1.length + part2.length];
        
        // part1을 answer에 복사
        System.arraycopy(part1, 0, answer, 0, part1.length);
        // part2를 answer에 복사
        System.arraycopy(part2, 0, answer, part1.length, part2.length);
        
        return answer;
    }
}
