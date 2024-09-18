import java.util.HashMap;

class Solution {
    public String solution(String letter) {
        // 모스 부호와 알파벳을 매핑한 HashMap 생성
        HashMap<String, String> morse = new HashMap<>();
        morse.put(".-", "a");
        morse.put("-...", "b");
        morse.put("-.-.", "c");
        morse.put("-..", "d");
        morse.put(".", "e");
        morse.put("..-.", "f");
        morse.put("--.", "g");
        morse.put("....", "h");
        morse.put("..", "i");
        morse.put(".---", "j");
        morse.put("-.-", "k");
        morse.put(".-..", "l");
        morse.put("--", "m");
        morse.put("-.", "n");
        morse.put("---", "o");
        morse.put(".--.", "p");
        morse.put("--.-", "q");
        morse.put(".-.", "r");
        morse.put("...", "s");
        morse.put("-", "t");
        morse.put("..-", "u");
        morse.put("...-", "v");
        morse.put(".--", "w");
        morse.put("-..-", "x");
        morse.put("-.--", "y");
        morse.put("--..", "z");

        // 공백을 기준으로 letter를 분리
        String[] morseLetters = letter.split(" ");
        StringBuilder result = new StringBuilder();

        // 각 모스 부호를 해독하여 결과에 추가
        for (String morseLetter : morseLetters) {
            result.append(morse.get(morseLetter));
        }

        // 해독된 결과 반환
        return result.toString();
    }
}
