def solution(s):
    # 길이가 1인 경우 바로 1 반환
    if len(s) == 1:
        return 1

    answer = len(s)

    # 1부터 len(s)//2까지 자르는 길이를 시도
    for step in range(1, len(s) // 2 + 1):
        compressed = ""
        prev = s[:step]   # 기준 문자열
        count = 1

        # step 단위로 문자열 순회
        for i in range(step, len(s), step):
            # 이전 문자열과 동일하면 count 증가
            if s[i:i + step] == prev:
                count += 1
            else:
                # 다르면 지금까지 결과 추가
                compressed += (str(count) + prev) if count > 1 else prev
                prev = s[i:i + step]
                count = 1

        # 마지막 덩어리 처리
        compressed += (str(count) + prev) if count > 1 else prev

        # 최솟값 갱신
        answer = min(answer, len(compressed))

    return answer
