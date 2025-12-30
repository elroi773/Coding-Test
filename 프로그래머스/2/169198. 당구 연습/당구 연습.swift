import Foundation

func solution(_ m: Int, _ n: Int, _ startX: Int, _ startY: Int, _ balls: [[Int]]) -> [Int] {
    func dist2(_ x1: Int, _ y1: Int, _ x2: Int, _ y2: Int) -> Int {
        let dx = x1 - x2
        let dy = y1 - y2
        return dx*dx + dy*dy
    }

    var res: [Int] = []
    res.reserveCapacity(balls.count)

    for b in balls {
        let bx = b[0], by = b[1]
        var best = Int.max

        // 1) 왼쪽 벽 반사: (-bx, by)
        // 왼쪽으로 직진하다가 벽 전에 공을 먼저 맞는 경우: startY == by && bx < startX
        if !(startY == by && bx < startX) {
            best = min(best, dist2(startX, startY, -bx, by))
        }

        // 2) 오른쪽 벽 반사: (2m - bx, by)
        // 오른쪽으로 직진하다가 벽 전에 공을 먼저 맞는 경우: startY == by && bx > startX
        if !(startY == by && bx > startX) {
            best = min(best, dist2(startX, startY, 2*m - bx, by))
        }

        // 3) 아래 벽 반사: (bx, -by)
        // 아래로 직진하다가 벽 전에 공을 먼저 맞는 경우: startX == bx && by < startY
        if !(startX == bx && by < startY) {
            best = min(best, dist2(startX, startY, bx, -by))
        }

        // 4) 위 벽 반사: (bx, 2n - by)
        // 위로 직진하다가 벽 전에 공을 먼저 맞는 경우: startX == bx && by > startY
        if !(startX == bx && by > startY) {
            best = min(best, dist2(startX, startY, bx, 2*n - by))
        }

        res.append(best)
    }

    return res
}