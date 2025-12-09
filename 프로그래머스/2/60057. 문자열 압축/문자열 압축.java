class Solution {
    public int solution(String s) {
        int len = s.length();
        
        // 길이가 1이면 압축 불가능 → 1
        if (len == 1) return 1;

        int answer = Integer.MAX_VALUE;

        // 자르는 단위: 1 ~ len/2
        for (int cut = 1; cut <= len / 2; cut++) {
            StringBuilder compressed = new StringBuilder();

            String prev = s.substring(0, cut);
            int count = 1;

            // cut 단위로 비교
            for (int i = cut; i < len; i += cut) {
                int end = Math.min(i + cut, len);
                String chunk = s.substring(i, end);

                if (chunk.equals(prev)) {
                    count++;
                } else {
                    if (count > 1) compressed.append(count);
                    compressed.append(prev);

                    prev = chunk;
                    count = 1;
                }
            }

            // 마지막 처리
            if (count > 1) compressed.append(count);
            compressed.append(prev);

            answer = Math.min(answer, compressed.length());
        }

        return answer;
    }
}
