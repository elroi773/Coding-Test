class Solution {
    fun solution(survey: Array<String>, choices: IntArray): String {
        // 성격 점수 저장용
        val scores = mutableMapOf(
            'R' to 0, 'T' to 0,
            'C' to 0, 'F' to 0,
            'J' to 0, 'M' to 0,
            'A' to 0, 'N' to 0
        )

        val points = arrayOf(3,2,1,0,1,2,3) // 선택지 1~7 점수 매핑

        for (i in survey.indices) {
            val (disagree, agree) = survey[i].toCharArray()
            val choice = choices[i]
            when {
                choice < 4 -> scores[disagree] = scores[disagree]!! + points[choice - 1]
                choice > 4 -> scores[agree] = scores[agree]!! + points[choice - 1]
            }
            // choice == 4 → 점수 없음
        }

        // 최종 성격 유형 결정
        val answer = StringBuilder()
        answer.append(if (scores['R']!! >= scores['T']!!) 'R' else 'T')
        answer.append(if (scores['C']!! >= scores['F']!!) 'C' else 'F')
        answer.append(if (scores['J']!! >= scores['M']!!) 'J' else 'M')
        answer.append(if (scores['A']!! >= scores['N']!!) 'A' else 'N')

        return answer.toString()
    }
}
