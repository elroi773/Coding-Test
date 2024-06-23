import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 첫째 줄에 x좌표를 입력 받습니다.
        int x = scanner.nextInt();
        
        // 둘째 줄에 y좌표를 입력 받습니다.
        int y = scanner.nextInt();

        // 사분면 판별 로직
        if (x > 0 && y > 0) {
            System.out.println(1); // 제1사분면
        } else if (x < 0 && y > 0) {
            System.out.println(2); // 제2사분면
        } else if (x < 0 && y < 0) {
            System.out.println(3); // 제3사분면
        } else if (x > 0 && y < 0) {
            System.out.println(4); // 제4사분면
        }

        scanner.close();
    }
}
