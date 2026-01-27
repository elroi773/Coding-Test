import Foundation

func solution(_ play_time: String, _ adv_time: String, _ logs: [String]) -> String {
    let playSec = toSec(play_time)
    let advSec = toSec(adv_time)

    // edge case: 광고 길이가 0이거나(문제상 거의 없음) 전체와 같으면 시작은 0
    if advSec >= playSec { return "00:00:00" }

    // diff 배열: 각 초의 시청자 변화량 기록
    // end 시각은 "종료 시각"으로, [start, end) 구간으로 처리
    var diff = Array(repeating: Int64(0), count: playSec + 2)

    for log in logs {
        // "HH:MM:SS-HH:MM:SS"
        let parts = log.split(separator: "-")
        let s = toSec(String(parts[0]))
        let e = toSec(String(parts[1]))
        diff[s] += 1
        diff[e] -= 1
    }

    // viewers[t] = t초에 재생 중인 시청자 수
    // prefixWatch[t] = 0초 ~ (t-1)초까지의 누적 재생시간 합 (초 단위 합)
    // 즉, 구간 [a, b) 누적 재생시간 = prefixWatch[b] - prefixWatch[a]
    var prefixWatch = Array(repeating: Int64(0), count: playSec + 2)

    var currentViewers: Int64 = 0
    for t in 0..<playSec {
        currentViewers += diff[t]
        prefixWatch[t + 1] = prefixWatch[t] + currentViewers
    }

    // 슬라이딩 윈도우로 최대 구간 찾기
    var bestStart = 0
    var bestValue: Int64 = -1

    // start in [0, playSec - advSec]
    for start in 0...(playSec - advSec) {
        let end = start + advSec
        let value = prefixWatch[end] - prefixWatch[start]
        if value > bestValue {
            bestValue = value
            bestStart = start
        }
        // value == bestValue 인 경우는 "가장 빠른 시작 시각" 유지 => 갱신 안 함
    }

    return toTime(bestStart)
}

// "HH:MM:SS" -> seconds
private func toSec(_ s: String) -> Int {
    let parts = s.split(separator: ":").map { Int($0)! }
    return parts[0] * 3600 + parts[1] * 60 + parts[2]
}

// seconds -> "HH:MM:SS"
private func toTime(_ sec: Int) -> String {
    let h = sec / 3600
    let m = (sec % 3600) / 60
    let s = sec % 60
    return String(format: "%02d:%02d:%02d", h, m, s)
}