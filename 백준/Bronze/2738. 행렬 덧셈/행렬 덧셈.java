import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 행렬의 크기 N과 M을 입력 받음
        int N = sc.nextInt();
        int M = sc.nextInt();

        // 행렬 A와 B를 생성
        int[][] A = new int[N][M];
        int[][] B = new int[N][M];

        // 행렬 A의 원소를 입력 받음
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                A[i][j] = sc.nextInt();
            }
        }

        // 행렬 B의 원소를 입력 받음
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                B[i][j] = sc.nextInt();
            }
        }

        // 행렬 A와 B를 더하여 행렬 C를 생성
        int[][] C = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }

        // 행렬 C를 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }

        sc.close();
    }
}
