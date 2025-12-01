def solution(places):
    answer = []
    
    dr = [-1, 1, 0, 0]
    dc = [0, 0, -1, 1]
    
    for place in places:
        ok = 1
        for r in range(5):
            for c in range(5):
                if place[r][c] != 'P':
                    continue
                
                # 거리 1 체크
                for k in range(4):
                    nr, nc = r + dr[k], c + dc[k]
                    if 0 <= nr < 5 and 0 <= nc < 5:
                        if place[nr][nc] == 'P':
                            ok = 0
                
                # 거리 2 체크
                dr2 = [-2, 2, 0, 0]
                dc2 = [0, 0, -2, 2]
                for k in range(4):
                    nr, nc = r + dr2[k], c + dc2[k]
                    if 0 <= nr < 5 and 0 <= nc < 5:
                        mr, mc = (r + nr) // 2, (c + nc) // 2
                        if place[nr][nc] == 'P' and place[mr][mc] != 'X':
                            ok = 0
                
                # 대각선 체크
                dr_diag = [-1, -1, 1, 1]
                dc_diag = [-1, 1, -1, 1]
                for k in range(4):
                    nr, nc = r + dr_diag[k], c + dc_diag[k]
                    if 0 <= nr < 5 and 0 <= nc < 5:
                        if place[nr][nc] == 'P':
                            if not (place[r][nc] == 'X' and place[nr][c] == 'X'):
                                ok = 0
        answer.append(ok)
    
    return answer
