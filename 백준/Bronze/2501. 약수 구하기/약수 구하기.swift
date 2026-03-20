import Foundation

let input = readLine()!.split(separator: " ").map { Int($0)! }
let n = input[0]
let k = input[1]

var count = 0

for i in 1...n {
    if n % i == 0 {
        count += 1
        if count == k {
            print(i)
            exit(0)
        }
    }
}

print(0)