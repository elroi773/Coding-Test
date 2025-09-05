def solution(nums):
    # 고를 수 있는 최대 마리 수
    pick = len(nums) // 2
    # 서로 다른 폰켓몬 종류 수
    kinds = len(set(nums))
    # 둘 중 작은 값이 최대 종류 수
    return min(pick, kinds)
