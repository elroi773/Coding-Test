from itertools import combinations

def solution(relation):
    row_len = len(relation)
    col_len = len(relation[0])
    candidate_keys = []

    # 1) 1개 컬럼부터 전체 컬럼까지 모든 조합 탐색
    for r in range(1, col_len + 1):
        for cols in combinations(range(col_len), r):

            # 2) 유일성 검사
            seen = set()
            for row in relation:
                key = tuple(row[c] for c in cols)
                seen.add(key)

            if len(seen) != row_len:
                continue  # 유일성 FAIL

            # 3) 최소성 검사
            minimal = True
            for ck in candidate_keys:
                # ck가 cols의 부분집합이면 최소성 위배
                if set(ck).issubset(set(cols)):
                    minimal = False
                    break

            if minimal:
                candidate_keys.append(cols)

    return len(candidate_keys)
