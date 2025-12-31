import Foundation

let arr = Array(readLine()!)
var i = 0
var count = 0

while i < arr.count {
    if i + 2 < arr.count && arr[i] == "d" && arr[i+1] == "z" && arr[i+2] == "=" {
        count += 1
        i += 3
    } else if i + 1 < arr.count {
        let a = arr[i], b = arr[i+1]
        if (a == "c" && (b == "=" || b == "-")) ||
           (a == "d" && b == "-") ||
           (a == "l" && b == "j") ||
           (a == "n" && b == "j") ||
           (a == "s" && b == "=") ||
           (a == "z" && b == "=") {
            count += 1
            i += 2
        } else {
            count += 1
            i += 1
        }
    } else {
        count += 1
        i += 1
    }
}

print(count)