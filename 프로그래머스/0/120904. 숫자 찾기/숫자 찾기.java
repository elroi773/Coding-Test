class Solution {
    public int solution(int num, int k) {
        // num을 문자열로 변환
        String numStr = String.valueOf(num);
        // k를 문자열로 변환
        String kStr = String.valueOf(k);

        // k가 문자열 numStr에서 처음 나타나는 위치 찾기
        int index = numStr.indexOf(kStr);

        // indexOf는 0부터 시작하므로, 1부터 시작하는 위치로 변환
        if (index != -1) {
            return index + 1;
        } else {
            return -1;
        }
    }
}
