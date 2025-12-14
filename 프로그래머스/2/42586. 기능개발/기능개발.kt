class Solution {
    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        val result = mutableListOf<Int>()

        // 1. 각 작업 완료까지 필요한 날짜 계산
        val days = IntArray(progresses.size)
        for (i in progresses.indices) {
            days[i] = (100 - progresses[i] + speeds[i] - 1) / speeds[i]
        }

        // 2. 배포 묶기
        var currentDay = days[0]
        var count = 1

        for (i in 1 until days.size) {
            if (days[i] <= currentDay) {
                count++
            } else {
                result.add(count)
                currentDay = days[i]
                count = 1
            }
        }

        // 마지막 배포
        result.add(count)

        return result.toIntArray()
    }
}
