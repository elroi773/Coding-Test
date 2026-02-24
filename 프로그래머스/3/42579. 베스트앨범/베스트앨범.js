function solution(genres, plays) {
  const answer = [];

  // 장르별 총 재생 수
  const genreTotal = new Map();

  // 장르별 노래 목록: [id, play]
  const genreSongs = new Map();

  for (let i = 0; i < genres.length; i++) {
    const genre = genres[i];
    const play = plays[i];

    genreTotal.set(genre, (genreTotal.get(genre) || 0) + play);

    if (!genreSongs.has(genre)) {
      genreSongs.set(genre, []);
    }
    genreSongs.get(genre).push([i, play]);
  }

  // 장르를 총 재생 수 기준 내림차순 정렬
  const sortedGenres = [...genreTotal.entries()]
    .sort((a, b) => b[1] - a[1])
    .map(([genre]) => genre);

  for (const genre of sortedGenres) {
    const songs = genreSongs.get(genre);

    // 장르 내 정렬
    songs.sort((a, b) => {
      // a = [id, play], b = [id, play]
      if (b[1] === a[1]) return a[0] - b[0]; // 재생 수 같으면 id 오름차순
      return b[1] - a[1]; // 재생 수 내림차순
    });

    // 최대 2곡 추가
    for (let i = 0; i < Math.min(2, songs.length); i++) {
      answer.push(songs[i][0]);
    }
  }

  return answer;
}