def solution(numbers):
    answer = []

    for num in numbers:
        # 1) 이진수 문자열 변환
        binary = bin(num)[2:]

        # 2) 포화 이진트리 길이 (2^k - 1)
        length = 1
        while length < len(binary):
            length = length * 2 + 1

        # 3) 앞에 0 패딩
        binary = binary.zfill(length)

        # 4) 유효성 검사
        answer.append(1 if is_valid(binary) else 0)

    return answer


def is_valid(tree):
    mid = len(tree) // 2

    # 루트가 0인데 하위에 1이 있으면 불가능
    if tree[mid] == '0' and '1' in tree:
        return False

    # 리프 노드
    if len(tree) == 1:
        return True

    left = tree[:mid]
    right = tree[mid + 1:]

    return is_valid(left) and is_valid(right)
