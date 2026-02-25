func solution(_ lines: [String]) -> Int {
    var intervals: [(start: Int, end: Int)] = []
    
    for line in lines {
        let parts = line.split(separator: " ")
        let time = String(parts[1])      // hh:mm:ss.sss
        let duration = String(parts[2])  // x.xxxs
        
        let endMs = parseTimeToMs(time)
        let durationMs = parseDurationToMs(duration)
        
        // 처리시간은 시작/끝 포함이므로 start = end - duration + 1
        let startMs = endMs - durationMs + 1
        intervals.append((start: startMs, end: endMs))
    }
    
    var answer = 0
    
    // 최대 처리량은 각 요청의 시작/끝 시점을 기준으로 한 1초 구간만 보면 충분
    for interval in intervals {
        let candidates = [interval.start, interval.end]
        
        for windowStart in candidates {
            let windowEnd = windowStart + 999 // [windowStart, windowStart+999]
            var count = 0
            
            for target in intervals {
                // 두 구간이 겹치면 카운트
                if target.end >= windowStart && target.start <= windowEnd {
                    count += 1
                }
            }
            
            if count > answer {
                answer = count
            }
        }
    }
    
    return answer
}

private func parseTimeToMs(_ time: String) -> Int {
    // hh:mm:ss.sss
    let t = time.split(separator: ":")
    let h = Int(t[0])!
    let m = Int(t[1])!
    
    let secParts = t[2].split(separator: ".")
    let s = Int(secParts[0])!
    let ms = Int(secParts[1])!
    
    return ((h * 3600 + m * 60 + s) * 1000) + ms
}

private func parseDurationToMs(_ duration: String) -> Int {
    // "2s", "2.0s", "0.351s"
    let raw = String(duration.dropLast()) // remove 's'
    
    if raw.contains(".") {
        let parts = raw.split(separator: ".")
        let sec = Int(parts[0])!
        var msStr = String(parts[1])
        
        // 소수점 아래를 3자리로 맞춤
        while msStr.count < 3 { msStr += "0" }
        if msStr.count > 3 { msStr = String(msStr.prefix(3)) }
        
        let ms = Int(msStr)!
        return sec * 1000 + ms
    } else {
        return Int(raw)! * 1000
    }
}