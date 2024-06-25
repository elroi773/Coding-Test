import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력을 받습니다.
        int N = scanner.nextInt(); // 과목의 개수
        double[] scores = new double[N];
        
        for (int i = 0; i < N; i++) {
            scores[i] = scanner.nextInt();
        }

        // 최대값을 찾습니다.
        double M = scores[0];
        for (int i = 1; i < N; i++) {
            if (scores[i] > M) {
                M = scores[i];
            }
        }

        // 새로운 점수로 변환합니다.
        double[] newScores = new double[N];
        double sum = 0;
        for (int i = 0; i < N; i++) {
            newScores[i] = (scores[i] / M) * 100;
            sum += newScores[i];
        }

        // 새로운 점수들의 평균을 계산합니다.
        double newAverage = sum / N;

        // 결과를 출력합니다.
        System.out.printf("%.2f", newAverage);
    }
}
