import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long A = sc.nextLong();
        long B = sc.nextLong();
        long V = sc.nextLong();

        long day = (V - A + (A - B) - 1) / (A - B) + 1;
        System.out.println(day);
    }
}