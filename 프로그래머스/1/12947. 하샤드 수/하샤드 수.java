class Solution {
    public boolean solution(int x) {
        // 자릿수 합을 구하기 위해서 x를 문자열로 변환하여 각 자릿수를 더함
        int sum = 0;
        int temp = x; // x의 값을 유지하기 위해 temp 변수에 저장

        while (temp > 0) {
            sum += temp % 10; // 자릿수를 추출해 sum에 더함
            temp /= 10;       // 자릿수를 줄이기 위해 temp를 10으로 나눔
        }

        // x가 자릿수 합으로 나누어 떨어지는지 확인
        return x % sum == 0;
    }
}
