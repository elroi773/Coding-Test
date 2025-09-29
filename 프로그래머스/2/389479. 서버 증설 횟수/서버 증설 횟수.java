import java.util.*;

public class Solution {
    public static int solution(int[] players, int m, int k) {
        int[] server = new int[players.length];
        int serverCnt = 0;

        for (int i = 0; i < players.length; i++) {
            int n = 0;
            if (players[i] >= m) {
                n = players[i] / m;

                int addServerCnt = 1;
                if (server[i] < n) {
                    addServerCnt = n - server[i];
                    if (n * m <= players[i] && players[i] < (n + 1) * m) {
                        serverCnt += addServerCnt;
                        for (int j = 0; j < k; j++) {
                            if (i + j < server.length) {
                                server[i + j] += addServerCnt;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }

        return serverCnt;
    }

    public static void main(String[] args) {
        int[] players = {1, 5, 10, 3, 12};
        System.out.println(solution(players, 3, 2)); // 테스트 출력
    }
}