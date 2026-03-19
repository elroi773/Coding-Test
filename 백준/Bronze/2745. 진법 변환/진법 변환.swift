import Foundation

let input = readLine()!.split(separator: " ")
let n = String(input[0])
let b = Int(input[1])!

var result = 0

for ch in n {
    let value: Int
    if ch >= "0" && ch <= "9" {
        value = Int(ch.unicodeScalars.first!.value - Character("0").unicodeScalars.first!.value)
    } else {
        value = Int(ch.unicodeScalars.first!.value - Character("A").unicodeScalars.first!.value) + 10
    }

    result = result * b + value
}

print(result)