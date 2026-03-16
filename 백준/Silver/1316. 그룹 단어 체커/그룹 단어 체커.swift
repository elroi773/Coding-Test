import Foundation

func isGroupWord(_ word: String) -> Bool {
    var visited = Array(repeating: false, count: 26)
    var prev: Character = " "

    for ch in word {
        if ch != prev {
            let index = Int(ch.asciiValue! - Character("a").asciiValue!)
            if visited[index] {
                return false
            }
            visited[index] = true
        }
        prev = ch
    }

    return true
}

let n = Int(readLine()!)!
var count = 0

for _ in 0..<n {
    let word = readLine()!
    if isGroupWord(word) {
        count += 1
    }
}

print(count)