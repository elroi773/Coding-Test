import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 첫째 줄에 정수의 개수 N을 입력 받습니다.
        int N = scanner.nextInt();

        // 둘째 줄에 N개의 정수를 입력 받습니다.
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = scanner.nextInt();
        }

        // 셋째 줄에 찾으려고 하는 정수 v를 입력 받습니다.
        int v = scanner.nextInt();

        // 정수 v의 개수를 셉니다.
        int count = 0;
        for (int number : numbers) {
            if (number == v) {
                count++;
            }
        }

        // 결과를 출력합니다.
        System.out.println(count);

        scanner.close();
    }
}

