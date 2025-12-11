def solution(skill, skill_trees):
    answer = 0

    for tree in skill_trees:
        # 선행 스킬 순서를 체크할 인덱스
        idx = 0
        valid = True

        for t in tree:
            # 해당 스킬이 선행 스킬 목록에 포함되면 확인
            if t in skill:
                # skill[idx] 와 같아야 정상 순서
                if t == skill[idx]:
                    idx += 1
                else:
                    valid = False
                    break
        
        if valid:
            answer += 1

    return answer
