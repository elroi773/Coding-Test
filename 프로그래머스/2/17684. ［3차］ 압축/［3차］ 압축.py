def solution(msg):
    # 1) 사전 초기화: A~Z => 1~26
    dic = {chr(ord('A') + i): i + 1 for i in range(26)}
    next_idx = 27

    answer = []
    i = 0
    n = len(msg)

    while i < n:
        # 2) 현재 위치에서 사전에 있는 가장 긴 문자열 w 찾기
        j = i + 1
        while j <= n and msg[i:j] in dic:
            j += 1

        # j가 한 칸 더 나간 상태이므로, 실제 w는 msg[i:j-1]
        w = msg[i:j-1]
        answer.append(dic[w])

        # 4) 다음 글자가 남아있다면 w+c를 사전에 등록
        if j <= n:
            wc = msg[i:j]  # w + 다음 글자
            dic[wc] = next_idx
            next_idx += 1

        # 3) 입력에서 w 제거(인덱스 이동)
        i += len(w)

    return answer