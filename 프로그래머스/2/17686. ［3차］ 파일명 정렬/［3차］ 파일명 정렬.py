def solution(files):
    parsed = []

    for idx, file in enumerate(files):
        head = ''
        number = ''
        i = 0

        # HEAD 추출
        while i < len(file) and not file[i].isdigit():
            head += file[i]
            i += 1

        # NUMBER 추출 (최대 5자리)
        while i < len(file) and file[i].isdigit() and len(number) < 5:
            number += file[i]
            i += 1

        parsed.append((head.lower(), int(number), idx, file))

    # 정렬 (HEAD → NUMBER → 입력순서)
    parsed.sort(key=lambda x: (x[0], x[1], x[2]))

    # 원본 파일명만 반환
    return [x[3] for x in parsed]
