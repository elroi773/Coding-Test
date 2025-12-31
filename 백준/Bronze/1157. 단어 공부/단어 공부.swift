import Foundation

let s = readLine()!.trimmingCharacters(in: .whitespacesAndNewlines)
var cnt = Array(repeating: 0, count: 26)

for scalar in s.unicodeScalars {
    let v = scalar.value
    let upper: UInt32
    if v >= 97 && v <= 122 { // a-z
        upper = v - 32
    } else {
        upper = v
    }
    cnt[Int(upper - 65)] += 1
}

let mx = cnt.max()!
let mxCount = cnt.filter { $0 == mx }.count

if mxCount > 1 {
    print("?")
} else {
    let idx = cnt.firstIndex(of: mx)!
    let ch = Character(UnicodeScalar(idx + 65)!)
    print(ch)
}