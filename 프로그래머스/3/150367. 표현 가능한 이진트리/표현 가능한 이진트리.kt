class Solution {

    fun solution(numbers: LongArray): IntArray {
        val answer = IntArray(numbers.size)

        for (i in numbers.indices) {
            val binary = numbers[i].toString(2)

            // 포화 이진트리 길이 찾기
            var len = 1
            while (len < binary.length) {
                len = len * 2 + 1
            }

            // 앞에 0 채우기
            val padded = binary.padStart(len, '0')

            answer[i] = if (isValid(padded)) 1 else 0
        }

        return answer
    }

    private fun isValid(tree: String): Boolean {
        val mid = tree.length / 2

        // 루트가 0인데 서브트리에 1이 있으면 불가능
        if (tree[mid] == '0' && tree.contains('1')) {
            return false
        }

        // 리프 노드
        if (tree.length == 1) return true

        val left = tree.substring(0, mid)
        val right = tree.substring(mid + 1)

        return isValid(left) && isValid(right)
    }
}
