class Solution {

    fun solution(arr: Array<IntArray>): IntArray {
        val result = IntArray(2) // result[0] = 0의 개수, result[1] = 1의 개수
        compress(arr, 0, 0, arr.size, result)
        return result
    }

    private fun compress(arr: Array<IntArray>, x: Int, y: Int, size: Int, result: IntArray) {
        // 현재 영역이 모두 같은 숫자인지 체크
        if (isSame(arr, x, y, size)) {
            result[arr[x][y]]++  // arr[x][y]가 0이면 result[0]++, 1이면 result[1]++
            return
        }

        val newSize = size / 2

        // 4개로 쪼개어 재귀 호출
        compress(arr, x, y, newSize, result)               // 좌상
        compress(arr, x, y + newSize, newSize, result)     // 우상
        compress(arr, x + newSize, y, newSize, result)     // 좌하
        compress(arr, x + newSize, y + newSize, newSize, result)  // 우하
    }

    private fun isSame(arr: Array<IntArray>, x: Int, y: Int, size: Int): Boolean {
        val value = arr[x][y]
        for (i in x until x + size) {
            for (j in y until y + size) {
                if (arr[i][j] != value) return false
            }
        }
        return true
    }
}
