import Foundation

func solution(_ n: Int, _ m: Int, _ x: Int, _ y: Int, _ queries: [[Int]]) -> Int64 {
    var r1 = Int64(x), r2 = Int64(x)
    var c1 = Int64(y), c2 = Int64(y)
    let nMax = Int64(n - 1)
    let mMax = Int64(m - 1)

    for i in stride(from: queries.count - 1, through: 0, by: -1) {
        let cmd = queries[i][0]
        let dx = Int64(queries[i][1])

        switch cmd {
        case 0: // (정방향) 왼쪽 -> (역방향) 오른쪽
            if c1 != 0 { c1 += dx }
            c2 = min(mMax, c2 + dx)

        case 1: // (정방향) 오른쪽 -> (역방향) 왼쪽
            c1 = max(0, c1 - dx)
            if c2 != mMax { c2 -= dx }

        case 2: // (정방향) 위 -> (역방향) 아래
            if r1 != 0 { r1 += dx }
            r2 = min(nMax, r2 + dx)

        default: // case 3: (정방향) 아래 -> (역방향) 위
            r1 = max(0, r1 - dx)
            if r2 != nMax { r2 -= dx }
        }

        if r1 > r2 || c1 > c2 { return 0 }
    }

    return (r2 - r1 + 1) * (c2 - c1 + 1)
}