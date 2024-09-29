class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        String[] words = {"aya", "ye", "woo", "ma"};
        
        for (String word : babbling) {
            String temp = word;
            boolean isPronounceable = true;
            
            // 각 단어에서 발음 가능한 부분을 빈 문자열로 치환
            for (String valid : words) {
                // 연속으로 같은 발음이 나오면 안됨
                if (temp.contains(valid + valid)) {
                    isPronounceable = false;
                    break;
                }
                temp = temp.replace(valid, " ");
            }
            
            // 치환 후 남은 문자열이 모두 빈 문자열일 경우 발음 가능
            if (isPronounceable && temp.trim().isEmpty()) {
                answer++;
            }
        }
        
        return answer;
    }
}
