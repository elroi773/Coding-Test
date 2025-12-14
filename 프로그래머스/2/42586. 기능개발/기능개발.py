def solution(progresses, speeds):
    answer = []
    
    # 1. 각 작업 완료까지 걸리는 날짜 계산
    days = []
    for p, s in zip(progresses, speeds):
        days.append((100 - p + s - 1) // s)
    
    # 2. 배포 묶기
    current_day = days[0]
    count = 1
    
    for i in range(1, len(days)):
        if days[i] <= current_day:
            count += 1
        else:
            answer.append(count)
            current_day = days[i]
            count = 1
    
    # 마지막 배포
    answer.append(count)
    
    return answer
