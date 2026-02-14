import Foundation

func solution(_ n: Int, _ computers: [[Int]]) -> Int {
    var visited = Array(repeating: false, count: n)
    var answer = 0

    func dfs(_ start: Int) {
        visited[start] = true
        for next in 0..<n {
            if computers[start][next] == 1 && !visited[next] {
                dfs(next)
            }
        }
    }

    for i in 0..<n {
        if !visited[i] {
            answer += 1
            dfs(i)
        }
    }

    return answer
}