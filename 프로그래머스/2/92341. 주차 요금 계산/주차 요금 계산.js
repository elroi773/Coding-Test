function solution(fees, records) {
    const [baseTime, baseFee, unitTime, unitFee] = fees;
    
    // 차량별 현재 입차 시각 (분 단위)
    const inMap = new Map();
    // 차량별 누적 주차 시간 (분 단위)
    const totalTime = new Map();
    
    // "HH:MM" -> 분 단위로 변환
    const timeToMinutes = (t) => {
        const [hh, mm] = t.split(':').map(Number);
        return hh * 60 + mm;
    };
    
    // 1. 입·출차 기록 처리
    for (const record of records) {
        const [timeStr, carNum, type] = record.split(' ');
        const minutes = timeToMinutes(timeStr);
        
        if (type === "IN") {
            inMap.set(carNum, minutes);
        } else { // OUT
            const inTime = inMap.get(carNum);
            inMap.delete(carNum);
            
            const diff = minutes - inTime;
            totalTime.set(carNum, (totalTime.get(carNum) || 0) + diff);
        }
    }
    
    // 2. 출차 기록이 없는 차량은 23:59에 출차된 것으로 간주
    const endOfDay = 23 * 60 + 59; // 1439
    for (const [carNum, inTime] of inMap.entries()) {
        const diff = endOfDay - inTime;
        totalTime.set(carNum, (totalTime.get(carNum) || 0) + diff);
    }
    
    // 3. 주차 요금 계산 함수
    const calcFee = (time) => {
        if (time <= baseTime) return baseFee;
        const extra = time - baseTime;
        const units = Math.ceil(extra / unitTime);
        return baseFee + units * unitFee;
    };
    
    // 4. 차량 번호 오름차순 정렬 후 요금 계산
    const cars = Array.from(totalTime.keys()).sort(); // '0000' ~ '9999' 문자열 정렬 = 번호 오름차순
    const answer = cars.map((car) => calcFee(totalTime.get(car)));
    
    return answer;
}