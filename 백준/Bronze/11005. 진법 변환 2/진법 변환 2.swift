import Foundation

let input = readLine()!.split(separator: " ").map { Int($0)! }
var n = input[0]
let b = input[1]

let digits = Array("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ")
var result = ""

while n > 0 {
    result.append(digits[n % b])
    n /= b
}

print(String(result.reversed()))