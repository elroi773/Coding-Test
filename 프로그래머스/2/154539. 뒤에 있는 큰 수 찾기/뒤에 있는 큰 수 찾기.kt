class Solution {
    fun solution(numbers: IntArray): IntArray {
        val n = numbers.size
        val answer = IntArray(n) { -1 } // 초기값 -1
        val stack = mutableListOf<Int>() // 인덱스를 저장할 스택

        for (i in numbers.indices) {
            // 현재 값이 스택 최상단 값보다 크면 뒷 큰수로 채움
            while (stack.isNotEmpty() && numbers[i] > numbers[stack.last()]) {
                val idx = stack.removeAt(stack.size - 1)
                answer[idx] = numbers[i]
            }
            stack.add(i) // 현재 인덱스를 스택에 추가
        }

        return answer
    }
}
