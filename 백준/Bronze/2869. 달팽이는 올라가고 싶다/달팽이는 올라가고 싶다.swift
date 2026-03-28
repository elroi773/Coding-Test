import Foundation

let input = readLine()!.split(separator: " ").map { Int64($0)! }
let A = input[0]
let B = input[1]
let V = input[2]

let day = (V - A + (A - B) - 1) / (A - B) + 1
print(day)