import java.util.*;

class Solution {
    public String solution(int[] numbers) {

        // 1) int 배열을 문자열 배열로 변환
        String[] arr = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            arr[i] = String.valueOf(numbers[i]);
        }

        // 2) 정렬 기준 설정 (핵심)
        Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));

        // 3) 모든 숫자가 0인 경우 처리
        if (arr[0].equals("0")) {
            return "0";
        }

        // 4) 문자열 합치기
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }

        return sb.toString();
    }
}
