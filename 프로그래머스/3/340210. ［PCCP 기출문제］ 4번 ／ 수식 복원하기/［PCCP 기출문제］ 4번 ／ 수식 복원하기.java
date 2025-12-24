import java.util.*;

class Solution {
    public String[] solution(String[] expressions) {
        List<Expr> parsed = new ArrayList<>();

        for (String exp : expressions) {
            String[] parts = exp.split(" ");
            parsed.add(new Expr(parts[0], parts[1], parts[2], parts[4], exp));
        }

        List<Integer> possibleBases = new ArrayList<>();

        // 1️⃣ 진법 후보 찾기
        for (int base = 2; base <= 9; base++) {
            boolean valid = true;

            for (Expr e : parsed) {
                Integer a = toDecimal(e.A, base);
                Integer b = toDecimal(e.B, base);

                if (a == null || b == null) {
                    valid = false;
                    break;
                }

                if (!e.C.equals("X")) {
                    Integer c = toDecimal(e.C, base);
                    if (c == null) {
                        valid = false;
                        break;
                    }

                    int result = e.op.equals("+") ? a + b : a - b;
                    if (result != c) {
                        valid = false;
                        break;
                    }
                }
            }

            if (valid) possibleBases.add(base);
        }

        // 2️⃣ X 계산
        List<String> answer = new ArrayList<>();

        for (Expr e : parsed) {
            if (!e.C.equals("X")) continue;

            Set<String> results = new HashSet<>();

            for (int base : possibleBases) {
                Integer a = toDecimal(e.A, base);
                Integer b = toDecimal(e.B, base);
                if (a == null || b == null) continue;

                int value = e.op.equals("+") ? a + b : a - b;
                results.add(Integer.toString(value, base));
            }

            String result = results.size() == 1 ? results.iterator().next() : "?";
            answer.add(e.raw.replace("X", result));
        }

        return answer.toArray(new String[0]);
    }

    // 🔹 진법 변환 + 자릿수 검증
    private Integer toDecimal(String s, int base) {
        int value = 0;
        for (char c : s.toCharArray()) {
            int digit = c - '0';
            if (digit >= base) return null;
            value = value * base + digit;
        }
        return value;
    }

    // 🔹 수식 클래스
    static class Expr {
        String A, op, B, C, raw;

        Expr(String A, String op, String B, String C, String raw) {
            this.A = A;
            this.op = op;
            this.B = B;
            this.C = C;
            this.raw = raw;
        }
    }
}
