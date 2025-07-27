def solution(players, callings):
    # 선수 이름 → 현재 등수 인덱스
    player_idx = {name: i for i, name in enumerate(players)}
    
    for name in callings:
        idx = player_idx[name]
        # 바로 앞 선수
        front = players[idx - 1]
        
        # 스왑
        players[idx - 1], players[idx] = players[idx], players[idx - 1]
        
        # 딕셔너리 업데이트
        player_idx[name] = idx - 1
        player_idx[front] = idx

    return players
