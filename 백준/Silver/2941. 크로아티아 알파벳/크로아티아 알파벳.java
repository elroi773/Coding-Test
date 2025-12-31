import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine().trim();

        String[] patterns = {"dz=", "c=", "c-", "d-", "lj", "nj", "s=", "z="};

        for (String p : patterns) {
            s = s.replace(p, "*");
        }

        System.out.println(s.length());
    }
}