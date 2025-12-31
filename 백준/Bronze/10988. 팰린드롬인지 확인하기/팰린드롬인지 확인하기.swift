import Foundation

let s = readLine()!.trimmingCharacters(in: .whitespacesAndNewlines)
let chars = Array(s)

var l = 0
var r = chars.count - 1

while l < r {
    if chars[l] != chars[r] {
        print(0)
        exit(0)
    }
    l += 1
    r -= 1
}

print(1)