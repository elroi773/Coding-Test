def solution(s):
    words = s.split(' ')
    transformed_words = []

    for word in words:
        new_word = ''
        for i, c in enumerate(word):
            if i % 2 == 0:
                new_word += c.upper()
            else:
                new_word += c.lower()
        transformed_words.append(new_word)

    answer = ' '.join(transformed_words)
    return answer
