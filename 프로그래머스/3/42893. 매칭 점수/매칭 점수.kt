import java.util.regex.Pattern
import kotlin.math.*

class Solution {

    private fun countWordOccurrences(html: String, targetLower: String): Int {
        val lower = html.lowercase()
        var cnt = 0
        val sb = StringBuilder()

        for (ch in lower) {
            if (ch in 'a'..'z') {
                sb.append(ch)
            } else {
                if (sb.isNotEmpty()) {
                    if (sb.toString() == targetLower) cnt++
                    sb.setLength(0)
                }
            }
        }
        if (sb.isNotEmpty() && sb.toString() == targetLower) cnt++
        return cnt
    }

    fun solution(word: String, pages: Array<String>): Int {
        val n = pages.size
        val target = word.lowercase()

        val urlP = Pattern.compile("<meta[^>]*property=\"og:url\"[^>]*content=\"([^\"]+)\"")
        val linkP = Pattern.compile("<a href=\"([^\"]+)\"")

        val urls = Array(n) { "" }
        val outLinks = Array(n) { mutableListOf<String>() }
        val base = DoubleArray(n)
        val linkScore = DoubleArray(n)

        val urlToIndex = HashMap<String, Int>(n * 2)

        // 1) parse url / outlinks / base score
        for (i in 0 until n) {
            val html = pages[i]

            // url
            run {
                val m = urlP.matcher(html)
                if (m.find()) {
                    urls[i] = m.group(1)
                    urlToIndex[urls[i]] = i
                }
            }

            // outlinks
            run {
                val m = linkP.matcher(html)
                while (m.find()) {
                    outLinks[i].add(m.group(1))
                }
            }

            // base score (word boundary by non-letters)
            base[i] = countWordOccurrences(html, target).toDouble()
        }

        // 2) distribute link score
        for (i in 0 until n) {
            val outCnt = outLinks[i].size
            if (outCnt == 0) continue
            val share = base[i] / outCnt.toDouble()

            for (to in outLinks[i]) {
                val j = urlToIndex[to]
                if (j != null) {
                    linkScore[j] += share
                }
            }
        }

        // 3) best matching score (tie -> smallest index)
        var bestIdx = 0
        var best = base[0] + linkScore[0]

        for (i in 1 until n) {
            val s = base[i] + linkScore[i]
            if (s > best) {
                best = s
                bestIdx = i
            }
        }

        return bestIdx
    }
}