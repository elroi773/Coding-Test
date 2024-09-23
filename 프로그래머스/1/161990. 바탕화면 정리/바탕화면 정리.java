class Solution {
    public int[] solution(String[] wallpaper) {
        // 최대, 최소 값을 추적하기 위한 변수들
        int lux = Integer.MAX_VALUE;
        int luy = Integer.MAX_VALUE;
        int rdx = Integer.MIN_VALUE;
        int rdy = Integer.MIN_VALUE;
        
        // 바탕화면을 순회하면서 "#"의 위치를 찾음
        for (int i = 0; i < wallpaper.length; i++) {
            for (int j = 0; j < wallpaper[i].length(); j++) {
                if (wallpaper[i].charAt(j) == '#') {
                    // 파일이 있는 위치에서 최소값과 최대값 갱신
                    lux = Math.min(lux, i);
                    luy = Math.min(luy, j);
                    rdx = Math.max(rdx, i);
                    rdy = Math.max(rdy, j);
                }
            }
        }
        
        // 드래그 끝점은 rdx + 1, rdy + 1이어야 함
        return new int[]{lux, luy, rdx + 1, rdy + 1};
    }
}
