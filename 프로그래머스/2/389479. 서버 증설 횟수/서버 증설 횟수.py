def solution(players, m, k):
    answer = 0
    """
    사람이 m명 늘어날때마다 서버 1대 추가로 해야함
    if time1_person < m:
        서버증설 X
    else n*m <= time2_person < (n+1) X m:
        최소 n대의 서버 운영 -> k시간 이후 반납
    return: 하루에 서버를 최소 몇번 증설하는지
    
     1 사람이 들어 왔을때 n*m 이 넘는지. n의 값이 뭔지
     1-2 넘으면 서버를 추가함 (증설 횟수에 추가)
     1-3 해당 서버는 K시간 이후에 자연 소멸
    """
    server = [0 for _ in range(len(players))]
    server_cnt = 0
    for i in range(len(players)):
        n = 0
        if players[i] >= m:
            n = players[i] // m
            
            # 기존 서버로 할당 가능한지 확인
            add_server_cnt = 1
            if server[i] < n:
                # 기존 서버로 할당 불가능하다면, 불가능한 수 만큼 증설
                add_server_cnt = n - server[i] # 증설해야할 서버 수
                if n * m <= players[i] < (n+1) * m:
                    server_cnt += add_server_cnt
                    for j in range(k):
                        if i + j < len(server):
                            server[i+j] += add_server_cnt
                            
                        else:
                            break
    
    return server_cnt