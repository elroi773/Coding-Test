def solution(dirs):
    moves = {'U': (0, 1), 'D': (0, -1), 'R': (1, 0), 'L': (-1, 0)}
    visited = set()  # 간선(길) 기록
    x, y = 0, 0

    for d in dirs:
        dx, dy = moves[d]
        nx, ny = x + dx, y + dy
        
        # 경계 체크
        if -5 <= nx <= 5 and -5 <= ny <= 5:
            # 양방향 길 기록 (정렬된 튜플 형태)
            path = ((x, y), (nx, ny))
            reverse_path = ((nx, ny), (x, y))
            
            if path not in visited and reverse_path not in visited:
                visited.add(path)
                visited.add(reverse_path)
            
            # 위치 업데이트
            x, y = nx, ny

    # 서로 반대방향을 둘 다 기록했으니 길의 개수는 절반!
    return len(visited) // 2
