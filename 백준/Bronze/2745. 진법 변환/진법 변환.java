import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String n = sc.next();
        int b = sc.nextInt();

        int result = 0;

        for (int i = 0; i < n.length(); i++) {
            char ch = n.charAt(i);
            int value;

            if (ch >= '0' && ch <= '9') {
                value = ch - '0';
            } else {
                value = ch - 'A' + 10;
            }

            result = result * b + value;
        }

        System.out.println(result);
    }
}