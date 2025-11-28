def solution(s):
    # 1. 바깥 {{ }} 제거
    s = s[2:-2]

    # 2. "},{" 기준으로 분리
    parts = s.split("},{")

    # 3. 각 부분을 숫자 리스트로 변환
    list_of_sets = [list(map(int, part.split(","))) for part in parts]

    # 4. 길이 기준 오름차순 정렬
    list_of_sets.sort(key=len)

    answer = []
    seen = set()

    # 5. 작은 집합부터 새로운 숫자만 추가
    for subset in list_of_sets:
        for num in subset:
            if num not in seen:
                seen.add(num)
                answer.append(num)

    return answer
