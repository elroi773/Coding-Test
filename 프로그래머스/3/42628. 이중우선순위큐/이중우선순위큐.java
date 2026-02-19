import java.util.PriorityQueue;
import java.util.Comparator;

class Solution {
    public int[] solution(String[] operations) {
        // (value, id)
        PriorityQueue<int[]> minH = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        PriorityQueue<int[]> maxH = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));

        boolean[] alive = new boolean[operations.length]; // id 유효 여부
        int nextId = 0;
        int validCount = 0;

        // top이 이미 삭제된 원소면 제거 (lazy deletion)
        Runnable cleanMin = () -> {
            while (!minH.isEmpty() && !alive[minH.peek()[1]]) minH.poll();
        };
        Runnable cleanMax = () -> {
            while (!maxH.isEmpty() && !alive[maxH.peek()[1]]) maxH.poll();
        };

        for (String op : operations) {
            char cmd = op.charAt(0);

            if (cmd == 'I') {
                int x = Integer.parseInt(op.substring(2));
                int id = nextId++;
                alive[id] = true;
                minH.offer(new int[]{x, id});
                maxH.offer(new int[]{x, id});
                validCount++;
            } else { // 'D'
                if (validCount == 0) continue;

                if (op.charAt(2) == '1') { // D 1 : delete max
                    cleanMax.run();
                    if (!maxH.isEmpty()) {
                        int id = maxH.poll()[1];
                        alive[id] = false;
                        validCount--;
                    }
                } else { // D -1 : delete min
                    cleanMin.run();
                    if (!minH.isEmpty()) {
                        int id = minH.poll()[1];
                        alive[id] = false;
                        validCount--;
                    }
                }
            }
        }

        if (validCount == 0) return new int[]{0, 0};

        cleanMin.run();
        cleanMax.run();
        return new int[]{maxH.peek()[0], minH.peek()[0]};
    }
}