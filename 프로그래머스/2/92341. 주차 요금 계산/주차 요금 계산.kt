class Solution {
    fun solution(fees: IntArray, records: Array<String>): IntArray {
        val baseTime = fees[0]   // 기본 시간(분)
        val baseFee = fees[1]    // 기본 요금(원)
        val unitTime = fees[2]   // 단위 시간(분)
        val unitFee = fees[3]    // 단위 요금(원)

        // 차량별 현재 주차 중인 입차 시각 (분)
        val inMap = mutableMapOf<String, Int>()
        // 차량별 누적 주차 시간 (분)
        val totalTime = mutableMapOf<String, Int>()

        fun timeToMinutes(t: String): Int {
            val (hh, mm) = t.split(":").map { it.toInt() }
            return hh * 60 + mm
        }

        // 1. 기록 처리
        for (record in records) {
            val parts = record.split(" ")
            val timeStr = parts[0]
            val carNum = parts[1]
            val type = parts[2]

            val minutes = timeToMinutes(timeStr)

            if (type == "IN") {
                inMap[carNum] = minutes
            } else { // "OUT"
                val inTime = inMap.remove(carNum)!!     // 항상 존재
                val diff = minutes - inTime
                totalTime[carNum] = totalTime.getOrDefault(carNum, 0) + diff
            }
        }

        // 2. 출차 기록 없는 차량들 23:59 처리
        val endOfDay = 23 * 60 + 59
        for ((carNum, inTime) in inMap) {
            val diff = endOfDay - inTime
            totalTime[carNum] = totalTime.getOrDefault(carNum, 0) + diff
        }

        // 3. 요금 계산 함수
        fun calcFee(time: Int): Int {
            if (time <= baseTime) return baseFee
            val extra = time - baseTime
            val units = (extra + unitTime - 1) / unitTime  // 올림
            return baseFee + units * unitFee
        }

        // 4. 차량 번호 오름차순 정렬 후 요금 배열 생성
        val cars = totalTime.keys.toMutableList()
        cars.sort()   // 문자열(4자리 숫자) 정렬 = 차량번호 오름차순

        val answer = IntArray(cars.size)
        for ((idx, car) in cars.withIndex()) {
            val t = totalTime[car]!!
            answer[idx] = calcFee(t)
        }

        return answer
    }
}