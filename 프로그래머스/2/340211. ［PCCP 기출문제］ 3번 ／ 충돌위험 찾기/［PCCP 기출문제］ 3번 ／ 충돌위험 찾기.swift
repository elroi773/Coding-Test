import Foundation

func solution(_ points: [[Int]], _ routes: [[Int]]) -> Int {
    let n = points.count
    let x = routes.count

    // 1-indexed로 좌표 저장
    var pr = Array(repeating: 0, count: n + 1)
    var pc = Array(repeating: 0, count: n + 1)
    for i in 0..<n {
        pr[i + 1] = points[i][0]
        pc[i + 1] = points[i][1]
    }

    // 각 로봇의 시간별 위치(path[t] = (r,c)) 생성
    var paths = Array(repeating: [(Int, Int)](), count: x)
    var maxT = 0

    for i in 0..<x {
        let route = routes[i]
        var path: [(Int, Int)] = []

        // 시작 위치 (t=0)
        var r = pr[route[0]]
        var c = pc[route[0]]
        path.append((r, c))

        // 다음 포인트로 이동 (r 먼저, 그 다음 c)
        if route.count >= 2 {
            for idx in 1..<route.count {
                let tr = pr[route[idx]]
                let tc = pc[route[idx]]

                while r != tr {
                    r += (r < tr) ? 1 : -1
                    path.append((r, c))
                }
                while c != tc {
                    c += (c < tc) ? 1 : -1
                    path.append((r, c))
                }
            }
        }

        paths[i] = path
        maxT = max(maxT, path.count - 1)
    }

    // 시간별로 같은 좌표에 2대 이상 있는 좌표 개수 합산
    var answer = 0
    var freq: [Int: Int] = [:]
    freq.reserveCapacity(256)

    if maxT >= 0 {
        for t in 0...maxT {
            freq.removeAll(keepingCapacity: true)

            for i in 0..<x {
                let path = paths[i]
                if t < path.count {
                    let (r, c) = path[t]
                    let key = r * 1000 + c   // r,c <= 100 이므로 유니크
                    freq[key, default: 0] += 1
                }
            }

            for (_, cnt) in freq where cnt >= 2 {
                answer += 1
            }
        }
    }

    return answer
}