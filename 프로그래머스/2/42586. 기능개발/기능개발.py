def solution(progresses, speeds):
    answer = []
    
    # 1. 각 작업의 완료까지 필요한 날짜 계산
    days = []
    for p, s in zip(progresses, speeds):
        days.append((100 - p + s - 1) // s)
    
    # 2. 배포 묶기
    current_day = days[0]
    count = 1
    
    for i in range(1, len(days)):
        if days[i] <= current_day:
            # 이전 기능과 함께 배포
            count += 1
        else:
            # 새로운 배포 시작
            answer.append(count)
            current_day = days[i]
            count = 1
    
    # 마지막 배포 추가
    answer.append(count)
    
    return answer
