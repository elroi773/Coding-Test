class Solution {
    public String solution(String p) {
        return convert(p);
    }

    // 1. 변환 함수
    private String convert(String p) {
        if (p.equals("")) return "";

        // 2. 균형잡힌 문자열 u, v로 분리
        int balance = 0;
        int idx = 0;

        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') balance++;
            else balance--;

            if (balance == 0) {
                idx = i;
                break;
            }
        }

        String u = p.substring(0, idx + 1);
        String v = p.substring(idx + 1);

        // 3. u가 올바른 괄호 문자열인지 체크
        if (isCorrect(u)) {
            // u 뒤에 v를 재귀 처리한 결과를 붙인다
            return u + convert(v);
        }

        // 4. u가 올바르지 않은 경우 규칙 수행
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(convert(v));
        sb.append(")");

        // u의 앞뒤 제거하고 나머지 뒤집기
        for (int i = 1; i < u.length() - 1; i++) {
            if (u.charAt(i) == '(') sb.append(")");
            else sb.append("(");
        }

        return sb.toString();
    }

    // 올바른 괄호 문자열인지 확인
    private boolean isCorrect(String s) {
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') cnt++;
            else cnt--;

            if (cnt < 0) return false;  // ')'가 먼저 나오는 경우
        }
        return cnt == 0;
    }
}
