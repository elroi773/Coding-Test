def solution(babbling):
    words = ["aya", "ye", "woo", "ma"]
    answer = 0

    for s in babbling:
        pos = 0
        last = ""
        valid = True

        while pos < len(s):
            matched = False
            for w in words:
                if s.startswith(w, pos) and w != last:
                    pos += len(w)
                    last = w
                    matched = True
                    break
            if not matched:
                valid = False
                break

        if valid:
            answer += 1

    return answer
