import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        boolean[] submitted = new boolean[31];  // 인덱스를 1부터 사용하기 위해 크기를 31로 설정

        // 과제 제출한 학생 번호 입력
        for (int i = 0; i < 28; i++) {
            int studentNumber = scanner.nextInt();
            submitted[studentNumber] = true;
        }

        // 제출하지 않은 학생 번호 출력
        for (int i = 1; i <= 30; i++) {
            if (!submitted[i]) {
                System.out.println(i);
            }
        }
        
        scanner.close();
    }
}
