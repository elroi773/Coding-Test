class Solution {

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            String binary = Long.toBinaryString(numbers[i]);

            // 포화 이진트리 길이 계산
            int len = 1;
            while (len < binary.length()) {
                len = len * 2 + 1;
            }

            // 앞에 0 채우기
            String padded = String.format("%" + len + "s", binary).replace(' ', '0');

            answer[i] = isValid(padded) ? 1 : 0;
        }

        return answer;
    }

    private boolean isValid(String tree) {
        int mid = tree.length() / 2;

        // 루트가 0인데 하위에 1 존재 → 불가능
        if (tree.charAt(mid) == '0' && tree.contains("1")) {
            return false;
        }

        // 리프 노드
        if (tree.length() == 1) return true;

        String left = tree.substring(0, mid);
        String right = tree.substring(mid + 1);

        return isValid(left) && isValid(right);
    }
}
