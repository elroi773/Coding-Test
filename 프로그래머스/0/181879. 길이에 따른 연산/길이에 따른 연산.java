class Solution {
    public int solution(int[] num_list) {
        int length = num_list.length;
        
        // 리스트의 길이가 11 이상인 경우
        if (length >= 11) {
            int sum = 0;
            for (int num : num_list) {
                sum += num;
            }
            return sum;
        } else {
            // 리스트의 길이가 10 이하인 경우
            int product = 1;
            for (int num : num_list) {
                product *= num;
            }
            return product;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // 테스트 예제 1 (길이가 11 이상)
        int[] num_list1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        System.out.println(sol.solution(num_list1)); // 출력: 77 (1+2+3+4+5+6+7+8+9+10+11)

        // 테스트 예제 2 (길이가 10 이하)
        int[] num_list2 = {1, 2, 3, 4, 5};
        System.out.println(sol.solution(num_list2)); // 출력: 120 (1*2*3*4*5)

        // 테스트 예제 3 (길이가 11 이상)
        int[] num_list3 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        System.out.println(sol.solution(num_list3)); // 출력: 78 (0+1+2+3+4+5+6+7+8+9+10+11+12)
    }
}
