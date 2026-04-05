import Foundation

var result = ""

while let line = readLine(), let n = Int(line) {
    if n == -1 { break }

    var divisors: [Int] = [1]

    if n > 1 {
        var i = 2
        while i * i <= n {
            if n % i == 0 {
                divisors.append(i)
                if i != n / i {
                    divisors.append(n / i)
                }
            }
            i += 1
        }
    }

    divisors.sort()
    let sum = divisors.reduce(0, +)

    if sum == n {
        result += "\(n) = "
        result += divisors.map { String($0) }.joined(separator: " + ")
        result += "\n"
    } else {
        result += "\(n) is NOT perfect.\n"
    }
}

print(result, terminator: "")