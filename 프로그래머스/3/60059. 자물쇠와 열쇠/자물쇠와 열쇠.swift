import Foundation

func solution(_ key: [[Int]], _ lock: [[Int]]) -> Bool {
    let m = key.count
    let n = lock.count
    
    // Lock을 가운데에 두기 위해 확장 보드 생성
    let pad = m - 1
    let size = n + pad * 2
    var board = Array(repeating: Array(repeating: 0, count: size), count: size)
    
    // 확장 보드 가운데에 lock 배치
    for i in 0..<n {
        for j in 0..<n {
            board[i + pad][j + pad] = lock[i][j]
        }
    }
    
    func rotate90(_ a: [[Int]]) -> [[Int]] {
        let len = a.count
        var r = Array(repeating: Array(repeating: 0, count: len), count: len)
        for i in 0..<len {
            for j in 0..<len {
                r[j][len - 1 - i] = a[i][j]
            }
        }
        return r
    }
    
    func isUnlocked(_ b: [[Int]]) -> Bool {
        // 중앙 lock 영역이 모두 1인지 확인
        for i in 0..<n {
            for j in 0..<n {
                if b[i + pad][j + pad] != 1 { return false }
            }
        }
        return true
    }
    
    var curKey = key
    
    for _ in 0..<4 {
        // key의 좌상단을 (x, y)에 두는 모든 이동 케이스 탐색
        // board 위에서 key가 완전히 올라갈 수 있는 범위: 0...(size - m)
        for x in 0...(size - m) {
            for y in 0...(size - m) {
                // key 올려서 더하기
                for i in 0..<m {
                    for j in 0..<m {
                        board[x + i][y + j] += curKey[i][j]
                    }
                }
                
                if isUnlocked(board) { return true }
                
                // 원복(빼기)
                for i in 0..<m {
                    for j in 0..<m {
                        board[x + i][y + j] -= curKey[i][j]
                    }
                }
            }
        }
        curKey = rotate90(curKey)
    }
    
    return false
}