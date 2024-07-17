class Solution {
    public String solution(String code) {
        StringBuilder ret = new StringBuilder();
        int mode = 0;

        for (int idx = 0; idx < code.length(); idx++) {
            char currentChar = code.charAt(idx);

            if (currentChar == '1') {
                mode = 1 - mode; // mode를 0에서 1로 또는 1에서 0으로 바꿈
            } else {
                if (mode == 0 && idx % 2 == 0) {
                    ret.append(currentChar);
                } else if (mode == 1 && idx % 2 == 1) {
                    ret.append(currentChar);
                }
            }
        }

        return ret.length() == 0 ? "EMPTY" : ret.toString();
    }
}
