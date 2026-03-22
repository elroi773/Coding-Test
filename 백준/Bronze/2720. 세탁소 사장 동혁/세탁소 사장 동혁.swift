import Foundation

let t = Int(readLine()!)!

for _ in 0..<t {
    var c = Int(readLine()!)!

    let q = c / 25
    c %= 25

    let d = c / 10
    c %= 10

    let n = c / 5
    c %= 5

    let p = c

    print("\(q) \(d) \(n) \(p)")
}