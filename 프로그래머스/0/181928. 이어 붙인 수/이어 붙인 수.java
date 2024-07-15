class Solution {
    public int solution(int[] num_list) {
        StringBuilder oddNumbers = new StringBuilder();
        StringBuilder evenNumbers = new StringBuilder();
        
        // 배열을 순회하면서 홀수와 짝수를 분리
        for (int num : num_list) {
            if (num % 2 == 0) {
                evenNumbers.append(num);
            } else {
                oddNumbers.append(num);
            }
        }
        
        // 홀수와 짝수 문자열을 정수로 변환
        int oddNumber = Integer.parseInt(oddNumbers.toString());
        int evenNumber = Integer.parseInt(evenNumbers.toString());
        
        // 두 수의 합을 반환
        return oddNumber + evenNumber;
    }
    
    // 메인 메서드 예시 테스트용
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(new int[]{3, 4, 5, 2, 1})); // 393
        System.out.println(sol.solution(new int[]{5, 7, 8, 3}));    // 581
    }
}
