class Solution {
    public int solution(int[] num_list) {
        // 배열을 순회하면서 음수를 찾음
        for (int i = 0; i < num_list.length; i++) {
            // 음수를 찾으면 해당 인덱스를 반환
            if (num_list[i] < 0) {
                return i;
            }
        }
        // 음수가 없다면 -1을 반환
        return -1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // 테스트 예제 1
        int[] num_list1 = {1, 2, 3, -4, 5};
        System.out.println(sol.solution(num_list1)); // 출력: 3

        // 테스트 예제 2
        int[] num_list2 = {1, 2, 3, 4, 5};
        System.out.println(sol.solution(num_list2)); // 출력: -1

        // 테스트 예제 3
        int[] num_list3 = {-1, 2, -3, 4, -5};
        System.out.println(sol.solution(num_list3)); // 출력: 0
    }
}
