class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];

        for (int idx = 0; idx < s.length; idx++) {
            String str = s[idx];

            // 1) "110" 전부 제거(스택 역할)
            StringBuilder stack = new StringBuilder(str.length());
            int cnt110 = 0;

            for (int i = 0; i < str.length(); i++) {
                stack.append(str.charAt(i));

                int n = stack.length();
                if (n >= 3
                        && stack.charAt(n - 3) == '1'
                        && stack.charAt(n - 2) == '1'
                        && stack.charAt(n - 1) == '0') {
                    stack.setLength(n - 3);
                    cnt110++;
                }
            }

            // 2) 삽입 위치: 남은 문자열에서 마지막 '0' 뒤 (없으면 0)
            int insertIdx = 0;
            for (int i = stack.length() - 1; i >= 0; i--) {
                if (stack.charAt(i) == '0') {
                    insertIdx = i + 1;
                    break;
                }
            }

            // 3) 결과 조립: prefix + ("110"*cnt) + suffix
            StringBuilder res = new StringBuilder(stack.length() + cnt110 * 3);

            res.append(stack, 0, insertIdx);
            for (int k = 0; k < cnt110; k++) res.append("110");
            res.append(stack, insertIdx, stack.length());

            answer[idx] = res.toString();
        }

        return answer;
    }
}