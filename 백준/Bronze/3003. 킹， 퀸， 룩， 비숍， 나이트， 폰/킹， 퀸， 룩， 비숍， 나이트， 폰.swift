import Foundation

let chess = [1, 1, 2, 2, 2, 8]
let input = readLine()!.split(separator: " ").map { Int($0)! }

for i in 0..<6 {
    print(chess[i] - input[i], terminator: " ")
}