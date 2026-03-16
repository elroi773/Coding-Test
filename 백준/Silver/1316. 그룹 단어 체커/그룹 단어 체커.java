import java.util.*;

class Main {
    public static boolean isGroupWord(String word) {
        boolean[] visited = new boolean[26];
        char prev = 0;

        for (int i = 0; i < word.length(); i++) {
            char current = word.charAt(i);

            if (current != prev) {
                if (visited[current - 'a']) {
                    return false;
                }
                visited[current - 'a'] = true;
            }

            prev = current;
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int count = 0;

        for (int i = 0; i < n; i++) {
            String word = sc.next();
            if (isGroupWord(word)) {
                count++;
            }
        }

        System.out.println(count);
    }
}