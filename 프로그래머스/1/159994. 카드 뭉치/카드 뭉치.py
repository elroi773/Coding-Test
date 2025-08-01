def solution(cards1, cards2, goal):
    i, j = 0, 0  # 각 카드덱의 포인터

    for word in goal:
        if i < len(cards1) and cards1[i] == word:
            i += 1
        elif j < len(cards2) and cards2[j] == word:
            j += 1
        else:
            return "No"  # 두 카드덱 어디에서도 현재 단어를 꺼낼 수 없으면 실패

    return "Yes"  # 전부 성공적으로 통과했다면
