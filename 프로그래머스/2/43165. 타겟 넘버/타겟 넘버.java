class Solution {
    int answer = 0;

    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        return answer;
    }

    // index: 현재 인덱스
    // sum: 지금까지의 합
    void dfs(int[] numbers, int target, int index, int sum) {
        // 모든 숫자를 사용한 경우
        if (index == numbers.length) {
            if (sum == target) {
                answer++;
            }
            return;
        }

        // 현재 숫자를 더하는 경우
        dfs(numbers, target, index + 1, sum + numbers[index]);

        // 현재 숫자를 빼는 경우
        dfs(numbers, target, index + 1, sum - numbers[index]);
    }
}
