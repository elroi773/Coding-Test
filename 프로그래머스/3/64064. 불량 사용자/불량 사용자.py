def solution(user_id, banned_id):
    # 패턴 매칭: 길이 같고 '*'는 와일드카드
    def match(user, ban):
        if len(user) != len(ban):
            return False
        for uc, bc in zip(user, ban):
            if bc == '*':
                continue
            if uc != bc:
                return False
        return True

    # banned_id 각 패턴별 후보(user index) 목록 만들기
    candidates = []
    for ban in banned_id:
        cand = []
        for i, user in enumerate(user_id):
            if match(user, ban):
                cand.append(i)
        candidates.append(cand)

    # 결과(순서 무관) 저장: 비트마스크(유저 최대 8명)
    results = set()

    def dfs(idx, used_mask):
        if idx == len(banned_id):
            results.add(used_mask)
            return
        for ui in candidates[idx]:
            bit = 1 << ui
            if used_mask & bit:
                continue
            dfs(idx + 1, used_mask | bit)

    dfs(0, 0)
    return len(results)