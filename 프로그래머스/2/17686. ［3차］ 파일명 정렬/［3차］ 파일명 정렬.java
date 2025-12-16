import java.util.*;

class Solution {

    static class FileInfo {
        String original;
        String headLower; // 비교용 (대소문자 무시)
        int number;       // 비교용 (정수)
        int index;        // 입력 순서 유지용

        FileInfo(String original, String headLower, int number, int index) {
            this.original = original;
            this.headLower = headLower;
            this.number = number;
            this.index = index;
        }
    }

    public String[] solution(String[] files) {
        FileInfo[] arr = new FileInfo[files.length];

        for (int i = 0; i < files.length; i++) {
            String s = files[i];
            int n = s.length();

            int p = 0;
            // 1) HEAD: 숫자 나오기 전까지
            while (p < n && !Character.isDigit(s.charAt(p))) p++;
            String head = s.substring(0, p);

            // 2) NUMBER: 최대 5자리 연속 숫자
            int startNum = p;
            int cnt = 0;
            while (p < n && Character.isDigit(s.charAt(p)) && cnt < 5) {
                p++;
                cnt++;
            }
            String numStr = s.substring(startNum, p);
            int num = Integer.parseInt(numStr);

            arr[i] = new FileInfo(s, head.toLowerCase(), num, i);
        }

        Arrays.sort(arr, (a, b) -> {
            int c1 = a.headLower.compareTo(b.headLower);
            if (c1 != 0) return c1;

            int c2 = Integer.compare(a.number, b.number);
            if (c2 != 0) return c2;

            // HEAD, NUMBER 같으면 입력 순서 유지
            return Integer.compare(a.index, b.index);
        });

        String[] answer = new String[files.length];
        for (int i = 0; i < arr.length; i++) {
            answer[i] = arr[i].original;
        }
        return answer;
    }
}