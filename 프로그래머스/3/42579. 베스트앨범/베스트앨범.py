def solution(genres, plays):
    # 장르별 총 재생 수
    genre_total = {}
    
    # 장르별 노래 목록: (고유번호, 재생수)
    genre_songs = {}
    
    for i, (genre, play) in enumerate(zip(genres, plays)):
        genre_total[genre] = genre_total.get(genre, 0) + play
        genre_songs.setdefault(genre, []).append((i, play))
    
    # 장르를 총 재생 수 기준 내림차순 정렬
    sorted_genres = sorted(genre_total.keys(), key=lambda g: genre_total[g], reverse=True)
    
    answer = []
    
    for genre in sorted_genres:
        # 장르 내 정렬:
        # 1) 재생 수 내림차순
        # 2) 재생 수 같으면 고유번호 오름차순
        songs = sorted(genre_songs[genre], key=lambda x: (-x[1], x[0]))
        
        # 최대 2곡 추가
        for i in range(min(2, len(songs))):
            answer.append(songs[i][0])
    
    return answer