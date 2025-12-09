import java.util.*;

class Solution {
    public long solution(String expression) {
        // 1. 숫자 리스트와 연산자 리스트 분리
        List<Long> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (char c : expression.toCharArray()) {
            if (c == '+' || c == '-' || c == '*') {
                nums.add(Long.parseLong(sb.toString()));
                sb.setLength(0);
                ops.add(c);
            } else {
                sb.append(c);
            }
        }
        nums.add(Long.parseLong(sb.toString()));

        // 등장한 연산자 추출
        Set<Character> set = new HashSet<>(ops);
        List<Character> uniqueOps = new ArrayList<>(set);

        long maxResult = 0;

        // 2. 연산자 우선순위 모든 순열 생성
        List<List<Character>> permutations = new ArrayList<>();
        generatePermutations(uniqueOps, 0, permutations);

        // 3. 각 우선순위 순열마다 계산
        for (List<Character> priority : permutations) {
            long result = evaluateByPriority(nums, ops, priority);
            maxResult = Math.max(maxResult, Math.abs(result));
        }
        return maxResult;
    }

    // 우선순위 순서대로 계산 수행
    private long evaluateByPriority(List<Long> nums, List<Character> ops, List<Character> priority) {
        List<Long> tmpNums = new ArrayList<>(nums);
        List<Character> tmpOps = new ArrayList<>(ops);

        for (char op : priority) {
            List<Long> newNums = new ArrayList<>();
            List<Character> newOps = new ArrayList<>();

            newNums.add(tmpNums.get(0));

            for (int i = 0; i < tmpOps.size(); i++) {
                if (tmpOps.get(i) == op) {
                    long a = newNums.remove(newNums.size() - 1);
                    long b = tmpNums.get(i + 1);
                    newNums.add(calc(a, b, op));
                } else {
                    newNums.add(tmpNums.get(i + 1));
                    newOps.add(tmpOps.get(i));
                }
            }
            tmpNums = newNums;
            tmpOps = newOps;
        }
        return tmpNums.get(0);
    }

    // 연산 함수
    private long calc(long a, long b, char op) {
        if (op == '+') return a + b;
        if (op == '-') return a - b;
        return a * b;
    }

    // 연산자 순열 만들기
    private void generatePermutations(List<Character> ops, int depth, List<List<Character>> result) {
        if (depth == ops.size()) {
            result.add(new ArrayList<>(ops));
            return;
        }

        for (int i = depth; i < ops.size(); i++) {
            Collections.swap(ops, i, depth);
            generatePermutations(ops, depth + 1, result);
            Collections.swap(ops, i, depth);
        }
    }
}
