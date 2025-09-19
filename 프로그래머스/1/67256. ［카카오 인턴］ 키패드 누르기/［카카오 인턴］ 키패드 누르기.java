class Solution {
    // 숫자를 (row, col) 좌표로 변환
    private int[] getPos(int num) {
        int[][] pos = {
            {3, 1}, // 0
            {0, 0}, {0, 1}, {0, 2},
            {1, 0}, {1, 1}, {1, 2},
            {2, 0}, {2, 1}, {2, 2}
        };
        return pos[num];
    }

    public String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();
        // 시작 위치: * = (3,0), # = (3,2)
        int[] left = {3, 0};
        int[] right = {3, 2};

        for (int num : numbers) {
            if (num == 1 || num == 4 || num == 7) {
                answer.append("L");
                left = getPos(num);
            } else if (num == 3 || num == 6 || num == 9) {
                answer.append("R");
                right = getPos(num);
            } else { // 2,5,8,0
                int[] target = getPos(num);
                int leftDist = Math.abs(left[0] - target[0]) + Math.abs(left[1] - target[1]);
                int rightDist = Math.abs(right[0] - target[0]) + Math.abs(right[1] - target[1]);

                if (leftDist < rightDist) {
                    answer.append("L");
                    left = target;
                } else if (rightDist < leftDist) {
                    answer.append("R");
                    right = target;
                } else { // 거리 같음 → hand 선호
                    if (hand.equals("left")) {
                        answer.append("L");
                        left = target;
                    } else {
                        answer.append("R");
                        right = target;
                    }
                }
            }
        }

        return answer.toString();
    }
}
