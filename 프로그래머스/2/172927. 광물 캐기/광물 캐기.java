import java.util.*;

class Solution {
    // 피로도 표 [곡괭이][광물]
    private static final int[][] fatigue = {
        {1, 1, 1},   // 다이아 곡괭이
        {5, 1, 1},   // 철 곡괭이
        {25, 5, 1}   // 돌 곡괭이
    };

    // 문자열을 숫자로 변환
    private int mineralToInt(String m) {
        switch (m) {
            case "diamond": return 0;
            case "iron": return 1;
            default: return 2; // stone
        }
    }

    public int solution(int[] picks, String[] minerals) {
        int totalPicks = picks[0] + picks[1] + picks[2];
        int groupCount = (minerals.length + 4) / 5;
        groupCount = Math.min(groupCount, totalPicks);

        // 1) 그룹 만들기
        List<int[]> groups = new ArrayList<>();
        for (int i = 0; i < groupCount; i++) {
            int size = Math.min(5, minerals.length - i * 5);
            int[] g = new int[size];
            for (int j = 0; j < size; j++) {
                g[j] = mineralToInt(minerals[i * 5 + j]);
            }
            groups.add(g);
        }

        // 2) 그룹 난이도 계산 (돌 곡괭이로 캤을 때)
        groups.sort((a, b) -> {
            int ha = 0, hb = 0;
            for (int x : a) ha += fatigue[2][x];
            for (int x : b) hb += fatigue[2][x];
            return hb - ha; // 내림차순
        });

        // 3) picks 배열을 곡괭이 리스트로 풀기 (0=다이아, 1=철, 2=돌)
        List<Integer> pickList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < picks[i]; j++) {
                pickList.add(i);
            }
        }
        Collections.sort(pickList); // 좋은 곡괭이부터

        // 4) 그룹별 피로도 계산
        int answer = 0;
        for (int i = 0; i < groups.size(); i++) {
            int pick = pickList.get(i);
            for (int m : groups.get(i)) {
                answer += fatigue[pick][m];
            }
        }

        return answer;
    }
}
