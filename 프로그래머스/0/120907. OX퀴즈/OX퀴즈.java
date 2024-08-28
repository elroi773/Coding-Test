class Solution {
    public String[] solution(String[] quiz) {
        String[] answer = new String[quiz.length];
        
        for (int i = 0; i < quiz.length; i++) {
            String[] parts = quiz[i].split(" ");
            int X = Integer.parseInt(parts[0]);
            int Y = Integer.parseInt(parts[2]);
            int Z = Integer.parseInt(parts[4]);
            answer[i] = (parts[1].equals("+") ? X + Y : X - Y) == Z ? "O" : "X";
        }
        
        return answer;
    }
}
