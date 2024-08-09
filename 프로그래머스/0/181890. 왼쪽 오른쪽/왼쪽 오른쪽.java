class Solution {
    public String[] solution(String[] str_list) {
        int lIndex = -1;
        int rIndex = -1;
        
        // "l"과 "r"의 인덱스 찾기
        for (int i = 0; i < str_list.length; i++) {
            if (str_list[i].equals("l")) {
                lIndex = i;
                break;
            }
        }
        for (int i = 0; i < str_list.length; i++) {
            if (str_list[i].equals("r")) {
                rIndex = i;
                break;
            }
        }
        
        // "l"이 먼저 나오는 경우
        if (lIndex != -1 && (rIndex == -1 || lIndex < rIndex)) {
            return java.util.Arrays.copyOfRange(str_list, 0, lIndex);
        }
        
        // "r"이 먼저 나오는 경우
        if (rIndex != -1 && (lIndex == -1 || rIndex < lIndex)) {
            return java.util.Arrays.copyOfRange(str_list, rIndex + 1, str_list.length);
        }
        
        // "l"과 "r"이 모두 없는 경우
        return new String[]{};
    }
}
