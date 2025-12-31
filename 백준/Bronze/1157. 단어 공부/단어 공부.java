import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine().trim();

        int[] cnt = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // 대문자로 통일
            if (c >= 'a' && c <= 'z') c = (char)(c - 32);

            cnt[c - 'A']++;
        }

        int max = -1;
        int maxIdx = -1;
        int maxCount = 0;

        for (int i = 0; i < 26; i++) {
            if (cnt[i] > max) {
                max = cnt[i];
                maxIdx = i;
                maxCount = 1;
            } else if (cnt[i] == max) {
                maxCount++;
            }
        }

        if (maxCount > 1) System.out.println("?");
        else System.out.println((char)('A' + maxIdx));
    }
}