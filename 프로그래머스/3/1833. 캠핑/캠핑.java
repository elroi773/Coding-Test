import java.util.*;

class Solution {
    public int solution(int n, int[][] data) {
        int[] xs = new int[n];
        int[] ys = new int[n];

        for (int i = 0; i < n; i++) {
            xs[i] = data[i][0];
            ys[i] = data[i][1];
        }

        int[] ux = uniqueSorted(xs);
        int[] uy = uniqueSorted(ys);

        int xLen = ux.length;
        int yLen = uy.length;
        int width = yLen + 1; // 1-based index 사용

        // 압축된 좌표 저장
        int[] cx = new int[n];
        int[] cy = new int[n];

        // 2차원 누적합을 1차원 배열로 표현
        int[] ps = new int[(xLen + 1) * (yLen + 1)];

        for (int i = 0; i < n; i++) {
            cx[i] = lowerBound(ux, data[i][0]) + 1;
            cy[i] = lowerBound(uy, data[i][1]) + 1;
            ps[cx[i] * width + cy[i]] = 1;
        }

        // 2차원 prefix sum 생성
        for (int x = 1; x <= xLen; x++) {
            int rowBase = x * width;
            int prevRowBase = (x - 1) * width;
            for (int y = 1; y <= yLen; y++) {
                int idx = rowBase + y;
                ps[idx] += ps[prevRowBase + y] + ps[rowBase + (y - 1)] - ps[prevRowBase + (y - 1)];
            }
        }

        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (cx[i] == cx[j] || cy[i] == cy[j]) {
                    continue; // 넓이 0
                }

                int x1 = Math.min(cx[i], cx[j]);
                int x2 = Math.max(cx[i], cx[j]);
                int y1 = Math.min(cy[i], cy[j]);
                int y2 = Math.max(cy[i], cy[j]);

                // 내부 영역이 아예 없으면 바로 가능
                if (x1 + 1 > x2 - 1 || y1 + 1 > y2 - 1) {
                    answer++;
                    continue;
                }

                int inside = query(ps, width, x1 + 1, y1 + 1, x2 - 1, y2 - 1);
                if (inside == 0) {
                    answer++;
                }
            }
        }

        return answer;
    }

    private int query(int[] ps, int width, int x1, int y1, int x2, int y2) {
        return get(ps, width, x2, y2)
             - get(ps, width, x1 - 1, y2)
             - get(ps, width, x2, y1 - 1)
             + get(ps, width, x1 - 1, y1 - 1);
    }

    private int get(int[] ps, int width, int x, int y) {
        if (x < 0 || y < 0) return 0;
        return ps[x * width + y];
    }

    private int[] uniqueSorted(int[] arr) {
        int[] temp = arr.clone();
        Arrays.sort(temp);

        int m = 0;
        for (int i = 0; i < temp.length; i++) {
            if (i == 0 || temp[i] != temp[i - 1]) {
                temp[m++] = temp[i];
            }
        }
        return Arrays.copyOf(temp, m);
    }

    private int lowerBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}