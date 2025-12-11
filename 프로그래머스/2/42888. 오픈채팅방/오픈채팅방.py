def solution(record):
    answer = []
    user = {}   # uid -> nickname 매핑 저장

    # 1) 최종 닉네임을 저장 (Enter, Change만 닉네임 변경)
    for rec in record:
        parts = rec.split()
        action = parts[0]
        uid = parts[1]
        if action in ("Enter", "Change"):
            nickname = parts[2]
            user[uid] = nickname

    # 2) 메시지 생성 (Enter, Leave만 메시지 출력)
    for rec in record:
        parts = rec.split()
        action = parts[0]
        uid = parts[1]

        if action == "Enter":
            answer.append(f"{user[uid]}님이 들어왔습니다.")
        elif action == "Leave":
            answer.append(f"{user[uid]}님이 나갔습니다.")

    return answer
