class Solution {
    public int solution(String s) {
        int answer = 0;
        int lastNumber = 0;
        String[] parts = s.split(" "); 
        for (String part : parts) {
            if (part.equals("Z")) {
                answer -= lastNumber;
            } else {
                lastNumber = Integer.parseInt(part);
                answer += lastNumber;
            }
        }

        return answer;
    }
}
