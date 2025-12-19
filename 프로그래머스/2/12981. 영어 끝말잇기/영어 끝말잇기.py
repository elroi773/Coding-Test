def solution(n, words):
    used = set()  # 이미 나온 단어들 저장

    for i in range(len(words)):
        word = words[i]

        # 1️⃣ 한 글자인 단어 체크
        if len(word) < 2:
            return [(i % n) + 1, (i // n) + 1]

        # 2️⃣ 이전에 나온 단어인지 체크
        if word in used:
            return [(i % n) + 1, (i // n) + 1]

        # 3️⃣ 끝말잇기 규칙 체크 (첫 단어 제외)
        if i > 0:
            prev_word = words[i - 1]
            if prev_word[-1] != word[0]:
                return [(i % n) + 1, (i // n) + 1]

        used.add(word)

    # 탈락자가 없는 경우
    return [0, 0]
