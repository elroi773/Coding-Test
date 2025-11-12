class Solution {
    fun solution(order: IntArray): Int {
        val stack = mutableListOf<Int>()  // 보조 컨테이너 벨트
        var current = 1                   // 다음에 꺼낼 상자 번호
        var answer = 0                    // 트럭에 실은 상자 수

        for (target in order) {
            // 필요한 상자가 나올 때까지 컨테이너에서 꺼내 보조 벨트에 보관
            while (current <= order.size && (stack.isEmpty() || stack.last() != target)) {
                stack.add(current)
                current++
            }

            // 스택의 top이 현재 필요한 상자라면 트럭에 실음
            if (stack.isNotEmpty() && stack.last() == target) {
                stack.removeAt(stack.size - 1)
                answer++
            } else {
                break // 더 이상 실을 수 없음
            }
        }

        return answer
    }
}
