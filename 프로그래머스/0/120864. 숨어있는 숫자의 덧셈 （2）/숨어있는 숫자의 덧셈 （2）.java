class Solution {
    public int solution(String my_string) {
        int sum = 0;
        String temp = ""; // 숫자를 임시로 저장할 변수

        // 문자열을 한 글자씩 탐색
        for (int i = 0; i < my_string.length(); i++) {
            char c = my_string.charAt(i);

            // 현재 문자가 숫자인 경우
            if (Character.isDigit(c)) {
                temp += c; // 숫자를 임시 문자열에 추가
            } else {
                // 숫자가 끝났으면 지금까지의 숫자를 더하고 초기화
                if (!temp.equals("")) {
                    sum += Integer.parseInt(temp); // 문자열을 정수로 변환하여 합산
                    temp = ""; // 임시 문자열 초기화
                }
            }
        }

        // 마지막 숫자가 끝난 후에 남아 있는 숫자가 있으면 더해줍니다.
        if (!temp.equals("")) {
            sum += Integer.parseInt(temp);
        }

        return sum; // 숫자의 합 반환
    }
}
