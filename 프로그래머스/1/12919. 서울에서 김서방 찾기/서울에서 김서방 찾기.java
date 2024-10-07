class Solution {
    public String solution(String[] seoul) {
        // "Kim"의 위치를 찾기 위한 변수
        int x = -1;

        // seoul 배열을 순회하여 "Kim"의 인덱스를 찾는다.
        for (int i = 0; i < seoul.length; i++) {
            if (seoul[i].equals("Kim")) {
                x = i; // "Kim"이 발견되면 그 인덱스를 x에 저장
                break; // 찾았으므로 반복문 종료
            }
        }

        // 결과 문자열을 생성하여 반환
        return "김서방은 " + x + "에 있다";
    }
}
