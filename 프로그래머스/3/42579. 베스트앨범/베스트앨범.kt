class Solution {
    fun solution(genres: Array<String>, plays: IntArray): IntArray {
        // 장르별 총 재생 수
        val genreTotal = mutableMapOf<String, Int>()

        // 장르별 노래 목록: Pair(고유번호, 재생수)
        val genreSongs = mutableMapOf<String, MutableList<Pair<Int, Int>>>()

        for (i in genres.indices) {
            val genre = genres[i]
            val play = plays[i]

            genreTotal[genre] = (genreTotal[genre] ?: 0) + play
            genreSongs.getOrPut(genre) { mutableListOf() }.add(i to play)
        }

        // 장르를 총 재생 수 기준 내림차순 정렬
        val sortedGenres = genreTotal.entries
            .sortedByDescending { it.value }
            .map { it.key }

        val result = mutableListOf<Int>()

        for (genre in sortedGenres) {
            val songs = genreSongs[genre] ?: mutableListOf()

            // 장르 내 정렬
            val sortedSongs = songs.sortedWith(
                compareByDescending<Pair<Int, Int>> { it.second } // 재생수 내림차순
                    .thenBy { it.first } // 같으면 고유번호 오름차순
            )

            // 최대 2곡 추가
            for (i in 0 until minOf(2, sortedSongs.size)) {
                result.add(sortedSongs[i].first)
            }
        }

        return result.toIntArray()
    }
}