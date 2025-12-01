class Solution {

    // 상하좌우
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for (int k = 0; k < 5; k++) {
            answer[k] = checkPlace(places[k]);
        }
        
        return answer;
    }
    
    private int checkPlace(String[] place) {
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (place[r].charAt(c) != 'P') continue;

                // 상하좌우 거리 1
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (nr >= 0 && nr < 5 && nc >= 0 && nc < 5) {
                        if (place[nr].charAt(nc) == 'P') return 0;
                    }
                }
                
                // 상하좌우 거리 2
                int[] dr2 = {-2, 2, 0, 0};
                int[] dc2 = {0, 0, -2, 2};
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr2[d];
                    int nc = c + dc2[d];
                    if (nr >= 0 && nr < 5 && nc >= 0 && nc < 5) {
                        int mr = (r + nr) / 2;
                        int mc = (c + nc) / 2;
                        if (place[nr].charAt(nc) == 'P' && place[mr].charAt(mc) != 'X') {
                            return 0;
                        }
                    }
                }

                // 대각선 체크
                int[] drDiag = {-1, -1, 1, 1};
                int[] dcDiag = {-1, 1, -1, 1};
                for (int d = 0; d < 4; d++) {
                    int nr = r + drDiag[d];
                    int nc = c + dcDiag[d];
                    if (nr >= 0 && nr < 5 && nc >= 0 && nc < 5) {
                        if (place[nr].charAt(nc) == 'P') {
                            if (!(place[r].charAt(nc) == 'X' && place[nr].charAt(c) == 'X')) {
                                return 0;
                            }
                        }
                    }
                }
            }
        }
        return 1;
    }
}
