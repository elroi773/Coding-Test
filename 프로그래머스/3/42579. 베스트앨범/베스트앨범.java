import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        // 장르별 총 재생 수
        Map<String, Integer> genreTotal = new HashMap<>();
        
        // 장르별 노래 목록
        // Song: (id, play)
        Map<String, List<Song>> genreSongs = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            genreTotal.put(genres[i], genreTotal.getOrDefault(genres[i], 0) + plays[i]);
            genreSongs.computeIfAbsent(genres[i], k -> new ArrayList<>())
                     .add(new Song(i, plays[i]));
        }
        
        // 장르를 총 재생 수 기준 내림차순 정렬
        List<String> sortedGenres = new ArrayList<>(genreTotal.keySet());
        sortedGenres.sort((a, b) -> genreTotal.get(b) - genreTotal.get(a));
        
        List<Integer> result = new ArrayList<>();
        
        for (String genre : sortedGenres) {
            List<Song> songs = genreSongs.get(genre);
            
            // 장르 내 정렬
            songs.sort((a, b) -> {
                if (b.play == a.play) {
                    return a.id - b.id; // 재생 수 같으면 고유번호 오름차순
                }
                return b.play - a.play; // 재생 수 내림차순
            });
            
            // 최대 2곡 추가
            for (int i = 0; i < Math.min(2, songs.size()); i++) {
                result.add(songs.get(i).id);
            }
        }
        
        // List<Integer> -> int[]
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
    
    static class Song {
        int id;
        int play;
        
        Song(int id, int play) {
            this.id = id;
            this.play = play;
        }
    }
}