class Solution {
    public String solution(String polynomial) {
        int xCoefficient = 0; // x의 계수를 저장할 변수
        int constant = 0;     // 상수항을 저장할 변수
        
        // 다항식을 공백을 기준으로 분리
        String[] terms = polynomial.split(" \\+ ");
        
        // 각 항을 처리
        for (String term : terms) {
            if (term.contains("x")) { // x가 포함된 경우
                // x의 계수를 추출 (x만 있는 경우 계수는 1)
                if (term.equals("x")) {
                    xCoefficient += 1;
                } else {
                    xCoefficient += Integer.parseInt(term.replace("x", ""));
                }
            } else { // 상수항인 경우
                constant += Integer.parseInt(term);
            }
        }
        
        // 결과를 저장할 StringBuilder
        StringBuilder result = new StringBuilder();
        
        // x의 계수가 0이 아닌 경우
        if (xCoefficient > 0) {
            if (xCoefficient == 1) {
                result.append("x");
            } else {
                result.append(xCoefficient).append("x");
            }
        }
        
        // 상수항이 있는 경우
        if (constant > 0) {
            if (result.length() > 0) {
                result.append(" + ");
            }
            result.append(constant);
        }
        
        // 결과가 없다면 0을 반환
        if (result.length() == 0) {
            return "0";
        }
        
        return result.toString();
    }
}
