import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int B = sc.nextInt();

        String digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();

        while (N > 0) {
            sb.append(digits.charAt(N % B));
            N /= B;
        }

        System.out.println(sb.reverse().toString());
    }
}