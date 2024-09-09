class Solution {
    public int solution(int slice, int n) {
        // 필요한 피자 판 수는 사람 수를 조각 수로 나눈 값에 올림을 적용
        int answer = (n + slice - 1) / slice;
        return answer;
    }
}
