class Solution {
    public int solution(int[][] points, int[][] routes) {
        int x = routes.length;       // 로봇 수
        int n = points.length;       // 포인트 수 (의미상)
        int m = routes[0].length;    // 각 로봇의 경로 길이

        // 로봇 상태
        int[] rs = new int[x];      // 현재 r
        int[] cs = new int[x];      // 현재 c
        int[] segIdx = new int[x];  // 현재 "도착 포인트" 인덱스 (1 ~ m-1)
        boolean[] active = new boolean[x]; // 센터 안에 있는지

        // 초기 상태: 각 로봇은 routes[i][0] 포인트에서 시작, routes[i][1]을 향해 이동
        for (int i = 0; i < x; i++) {
            int startPointId = routes[i][0] - 1; // 0-based
            rs[i] = points[startPointId][0];
            cs[i] = points[startPointId][1];
            segIdx[i] = 1;      // routes[i][0] -> routes[i][1]
            active[i] = true;
        }

        // 좌표 범위: r, c는 1~100
        int[][] visit = new int[101][101]; // tick 버전 관리
        int[][] cnt = new int[101][101];   // 해당 tick에서의 로봇 수
        int tick = 1;
        int answer = 0;

        while (true) {
            boolean anyActive = false;
            int collisionsThisTime = 0;

            // 1) 현재 시각의 위치로 충돌 카운트
            for (int i = 0; i < x; i++) {
                if (!active[i]) continue;

                anyActive = true;
                int r = rs[i];
                int c = cs[i];

                if (visit[r][c] != tick) {
                    // 이번 tick에 처음 방문하는 좌표
                    visit[r][c] = tick;
                    cnt[r][c] = 1;
                } else {
                    cnt[r][c]++;
                    // 1 -> 2가 되는 순간만 위험 상황 1회 추가
                    if (cnt[r][c] == 2) {
                        collisionsThisTime++;
                    }
                }
            }

            // 더 이상 센터 안에 로봇이 없으면 종료
            if (!anyActive) break;

            // 이 시각에 발생한 위험 상황 개수 합산
            answer += collisionsThisTime;

            // 2) 다음 시각으로 이동
            for (int i = 0; i < x; i++) {
                if (!active[i]) continue;

                int idx = segIdx[i];    // 현재 도착 포인트 인덱스

                // 안전 장치: 이론상 여기 올 일은 거의 없음
                if (idx >= m) {
                    active[i] = false;
                    continue;
                }

                int targetId = routes[i][idx] - 1;
                int tr = points[targetId][0];
                int tc = points[targetId][1];

                // 이미 목표 포인트에 서 있는 경우
                if (rs[i] == tr && cs[i] == tc) {
                    if (idx == m - 1) {
                        // 마지막 포인트에 도착 → 다음 시각부터 센터 밖
                        active[i] = false;
                        continue;
                    } else {
                        // 다음 구간으로 넘어감 (route[idx] -> route[idx+1])
                        idx++;
                        segIdx[i] = idx;
                        targetId = routes[i][idx] - 1;
                        tr = points[targetId][0];
                        tc = points[targetId][1];
                    }
                }

                // 아직 움직일 로봇이면 목표 좌표를 향해 r 우선 한 칸 이동
                if (!active[i]) continue; // 위에서 마지막 포인트로 끝난 경우 방지

                if (rs[i] != tr) {
                    rs[i] += (tr > rs[i]) ? 1 : -1;
                } else if (cs[i] != tc) {
                    cs[i] += (tc > cs[i]) ? 1 : -1;
                }
                // 둘 다 같을 경우는 위에서 처리됨
            }

            tick++;
        }

        return answer;
    }
}