import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        boolean[] submitted = new boolean[31];  

        for (int i = 0; i < 28; i++) {
            int studentNumber = scanner.nextInt();
            submitted[studentNumber] = true;
        }//제출한 사람은 28명 0부터 시작이니까 < 

        for (int i = 1; i <= 30; i++) {
            if (!submitted[i]) {
                System.out.println(i);
            }
        }
        //for 문 돌면서 sub보면서 다르면 이제 제출 안한 학생
        
        scanner.close();
    }
}
