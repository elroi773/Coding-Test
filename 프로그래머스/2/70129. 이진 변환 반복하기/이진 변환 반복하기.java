class Solution {
    public int[] solution(String s) {
        int transformCnt = 0;
        int removedZeroCnt = 0;

        while (!s.equals("1")) {
            int ones = 0;
            int len = s.length();
            for (int i = 0; i < len; i++) {
                if (s.charAt(i) == '1') ones++;
            }
            removedZeroCnt += (len - ones);
            transformCnt++;

            // 다음 문자열 = 1의 개수의 2진수 표현
            s = Integer.toBinaryString(ones);
        }

        return new int[] { transformCnt, removedZeroCnt };
    }
}