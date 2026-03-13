import Foundation

func solution(_ s: String) -> Int {
    let chars = Array(s)
    let n = chars.count
    
    func expand(_ leftStart: Int, _ rightStart: Int) -> Int {
        var left = leftStart
        var right = rightStart
        
        while left >= 0 && right < n && chars[left] == chars[right] {
            left -= 1
            right += 1
        }
        
        return right - left - 1
    }
    
    var answer = 1
    
    for i in 0..<n {
        let odd = expand(i, i)       // 홀수 길이
        let even = expand(i, i + 1)  // 짝수 길이
        
        answer = max(answer, odd, even)
    }
    
    return answer
}