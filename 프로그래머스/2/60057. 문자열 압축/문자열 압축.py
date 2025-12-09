def solution(s):
    # 예외: 길이가 1이면 압축 불가능 → 길이 1
    if len(s) == 1:
        return 1
    
    answer = float('inf')

    # 1부터 s 길이의 절반까지 자르는 단위 길이를 검사
    for cut in range(1, len(s) // 2 + 1):
        compressed = ""
        prev = s[:cut]   # 첫 조각
        count = 1

        # cut 단위로 문자열 쭉 비교
        for i in range(cut, len(s), cut):
            chunk = s[i:i+cut]
            if chunk == prev:
                count += 1
            else:
                # 이전 반복 덩어리 추가
                compressed += (str(count) + prev) if count > 1 else prev
                prev = chunk
                count = 1

        # 마지막 조각 처리
        compressed += (str(count) + prev) if count > 1 else prev

        # 가장 짧은 길이 갱신
        answer = min(answer, len(compressed))

    return answer
