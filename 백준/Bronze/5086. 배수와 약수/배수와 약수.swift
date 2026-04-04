import Foundation

while let line = readLine() {
    let input = line.split(separator: " ").map { Int($0)! }
    let a = input[0]
    let b = input[1]

    if a == 0 && b == 0 {
        break
    }

    if b % a == 0 {
        print("factor")
    } else if a % b == 0 {
        print("multiple")
    } else {
        print("neither")
    }
}