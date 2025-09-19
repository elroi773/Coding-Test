def solution(survey, choices):
    # 점수 저장
    scores = {
        'R': 0, 'T': 0,
        'C': 0, 'F': 0,
        'J': 0, 'M': 0,
        'A': 0, 'N': 0
    }
    
    # 선택지별 점수: 1~7 → 3,2,1,0,1,2,3
    points = [3, 2, 1, 0, 1, 2, 3]

    # 각 질문 처리
    for s, choice in zip(survey, choices):
        disagree, agree = s[0], s[1]
        if choice < 4:  # 비동의
            scores[disagree] += points[choice - 1]
        elif choice > 4:  # 동의
            scores[agree] += points[choice - 1]
        # choice == 4 → 점수 없음

    # 최종 성격 유형 결정
    answer = ''
    answer += 'R' if scores['R'] >= scores['T'] else 'T'
    answer += 'C' if scores['C'] >= scores['F'] else 'F'
    answer += 'J' if scores['J'] >= scores['M'] else 'M'
    answer += 'A' if scores['A'] >= scores['N'] else 'N'

    return answer
