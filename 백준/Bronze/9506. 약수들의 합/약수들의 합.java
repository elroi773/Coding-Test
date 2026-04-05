import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == -1) break;

            List<Integer> divisors = new ArrayList<>();
            divisors.add(1);

            for (int i = 2; i * i <= n; i++) {
                if (n % i == 0) {
                    divisors.add(i);
                    if (i != n / i) divisors.add(n / i);
                }
            }

            Collections.sort(divisors);

            int sum = 0;
            for (int d : divisors) sum += d;

            if (sum == n) {
                sb.append(n).append(" = ");
                for (int i = 0; i < divisors.size(); i++) {
                    sb.append(divisors.get(i));
                    if (i != divisors.size() - 1) sb.append(" + ");
                }
                sb.append('\n');
            } else {
                sb.append(n).append(" is NOT perfect.\n");
            }
        }

        System.out.print(sb);
    }
}