class Solution {
    fun solution(play_time: String, adv_time: String, logs: Array<String>): String {
        val playSec = toSec(play_time)
        val advSec = toSec(adv_time)

        if (advSec >= playSec) return "00:00:00"

        // diff[t] = t초에서 시청자 수 변화량
        val diff = LongArray(playSec + 2)

        // [start, end) 로 처리
        for (log in logs) {
            val dash = log.indexOf('-')
            val s = toSec(log.substring(0, dash))
            val e = toSec(log.substring(dash + 1))
            diff[s] += 1L
            diff[e] -= 1L
        }

        // prefixWatch[t] = 0초 ~ (t-1)초까지 누적 재생시간(초 합)
        val prefixWatch = LongArray(playSec + 2)
        var viewers = 0L

        for (t in 0 until playSec) {
            viewers += diff[t]
            prefixWatch[t + 1] = prefixWatch[t] + viewers
        }

        var bestStart = 0
        var bestValue = -1L

        for (start in 0..(playSec - advSec)) {
            val end = start + advSec
            val value = prefixWatch[end] - prefixWatch[start]
            if (value > bestValue) {
                bestValue = value
                bestStart = start
            }
            // 같으면 더 빠른 bestStart 유지 (갱신 X)
        }

        return toTime(bestStart)
    }

    private fun toSec(time: String): Int {
        val h = (time[0] - '0') * 10 + (time[1] - '0')
        val m = (time[3] - '0') * 10 + (time[4] - '0')
        val s = (time[6] - '0') * 10 + (time[7] - '0')
        return h * 3600 + m * 60 + s
    }

    private fun toTime(sec: Int): String {
        val h = sec / 3600
        val m = (sec % 3600) / 60
        val s = sec % 60
        return "%02d:%02d:%02d".format(h, m, s)
    }
}