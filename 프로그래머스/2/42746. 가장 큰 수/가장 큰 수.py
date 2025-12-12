def solution(numbers):
    # 1) 숫자를 문자열로 변환
    nums = list(map(str, numbers))
    
    # 2) 정렬 기준 설정 — 가장 중요한 핵심!
    nums.sort(key=lambda x: x*3, reverse=True)

    # 3) 0으로만 구성된 경우 처리 (예: [0, 0, 0])
    if nums[0] == "0":
        return "0"

    # 4) 문자열로 이어붙여서 반환
    return "".join(nums)
