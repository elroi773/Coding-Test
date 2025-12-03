import math

def solution(fees, records):
    base_time, base_fee, unit_time, unit_fee = fees
    
    in_dict = {}      # 차량번호 -> 입차 시각(분)
    total_dict = {}   # 차량번호 -> 누적 주차 시간(분)
    
    def time_to_minutes(t: str) -> int:
        hh, mm = map(int, t.split(':'))
        return hh * 60 + mm
    
    # 1. 입·출차 기록 처리
    for record in records:
        time_str, car, typ = record.split()
        minutes = time_to_minutes(time_str)
        
        if typ == "IN":
            in_dict[car] = minutes
        else:  # "OUT"
            in_time = in_dict.pop(car)
            diff = minutes - in_time
            total_dict[car] = total_dict.get(car, 0) + diff
    
    # 2. 출차 기록이 없는 차량은 23:59에 출차된 것으로 간주
    end_of_day = 23 * 60 + 59  # 1439분
    for car, in_time in in_dict.items():
        diff = end_of_day - in_time
        total_dict[car] = total_dict.get(car, 0) + diff
    
    # 3. 요금 계산 함수
    def calc_fee(time: int) -> int:
        if time <= base_time:
            return base_fee
        extra = time - base_time
        units = math.ceil(extra / unit_time)
        return base_fee + units * unit_fee
    
    # 4. 차량번호 오름차순 정렬 후 요금 계산
    answer = []
    for car in sorted(total_dict.keys()):
        answer.append(calc_fee(total_dict[car]))
    
    return answer