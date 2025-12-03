class Solution {
    // 전역(필드) 변수들
    int nArrows;        // 라이언 화살 개수
    int[] apeach = new int[11]; // 어피치 info
    int[] cur = new int[11];    // 현재 DFS 중인 라이언 배치
    int[] best = new int[11];   // 최종 최적 배치
    int bestDiff = 0;           // 최대 점수 차이
    boolean hasAnswer = false;  // 이길 수 있는 경우를 찾았는지 여부

    public int[] solution(int n, int[] info) {
        nArrows = n;
        // 어피치 정보 복사
        for (int i = 0; i < 11; i++) {
            apeach[i] = info[i];
            cur[i] = 0;
            best[i] = 0;
        }

        dfs(0, nArrows);

        if (!hasAnswer) {
            return new int[]{-1};
        }
        return best;
    }

    // idx: 현재 점수 인덱스(0 -> 10), arrowsLeft: 남은 화살 수
    private void dfs(int idx, int arrowsLeft) {
        // 마지막 점수(0점 = 인덱스 10) 처리
        if (idx == 10) {
            // 남은 화살 모두 0점에 사용
            cur[10] = arrowsLeft;

            int rScore = 0;
            int aScore = 0;

            // 점수 계산
            for (int i = 0; i < 11; i++) {
                if (apeach[i] == 0 && cur[i] == 0) continue;

                int point = 10 - i;
                if (cur[i] > apeach[i]) {
                    rScore += point;
                } else {
                    aScore += point;
                }
            }

            int diff = rScore - aScore;

            // 라이언이 이기지 못하면 무시
            if (diff <= 0) {
                cur[10] = 0;   // 원상복구
                return;
            }

            // 처음 찾은 승리 경우거나, 점수 차이가 더 크면 갱신
            if (!hasAnswer || diff > bestDiff) {
                bestDiff = diff;
                hasAnswer = true;
                best = cur.clone();
            }
            // 점수 차이가 같다면, 낮은 점수(인덱스 큰 쪽)를 더 많이 맞힌 경우 선택
            else if (diff == bestDiff) {
                for (int i = 10; i >= 0; i--) {
                    if (cur[i] > best[i]) {
                        best = cur.clone();
                        break;
                    } else if (cur[i] < best[i]) {
                        break;
                    }
                }
            }

            cur[10] = 0; // 원상복구
            return;
        }

        // 1) 현재 점수(10 - idx)를 따기 위해 어피치보다 1발 더 쏘는 경우
        int need = apeach[idx] + 1;
        if (need <= arrowsLeft) {
            cur[idx] = need;
            dfs(idx + 1, arrowsLeft - need);
            cur[idx] = 0;  // 백트래킹
        }

        // 2) 현재 점수 포기 (0발 쏘는 경우)
        dfs(idx + 1, arrowsLeft);
    }
}