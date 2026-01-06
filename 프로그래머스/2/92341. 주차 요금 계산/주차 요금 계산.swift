import Foundation

func solution(_ fees: [Int], _ records: [String]) -> [Int] {
    let baseTime = fees[0]
    let baseFee  = fees[1]
    let unitTime = fees[2]
    let unitFee  = fees[3]

    func toMinutes(_ hhmm: String) -> Int {
        let parts = hhmm.split(separator: ":")
        let h = Int(parts[0])!
        let m = Int(parts[1])!
        return h * 60 + m
    }

    // car -> last IN time
    var inTime: [String: Int] = [:]
    // car -> total parked minutes
    var total: [String: Int] = [:]

    for rec in records {
        let parts = rec.split(separator: " ")
        let timeStr = String(parts[0])
        let car = String(parts[1])
        let type = String(parts[2])

        let t = toMinutes(timeStr)

        if type == "IN" {
            inTime[car] = t
            // 등장만 하고 아직 합산이 없을 수 있으니 0으로 초기화
            if total[car] == nil { total[car] = 0 }
        } else { // OUT
            if let enter = inTime[car] {
                total[car, default: 0] += (t - enter)
                inTime[car] = nil
            }
        }
    }

    // 출차 기록 없는 차량은 23:59 출차 처리
    let end = 23 * 60 + 59
    for (car, enter) in inTime {
        total[car, default: 0] += (end - enter)
    }

    // 요금 계산
    func calcFee(_ minutes: Int) -> Int {
        if minutes <= baseTime { return baseFee }
        let extra = minutes - baseTime
        let units = (extra + unitTime - 1) / unitTime  // 올림
        return baseFee + units * unitFee
    }

    // 차량번호 오름차순
    let cars = total.keys.sorted()

    return cars.map { car in
        calcFee(total[car]!)
    }
}