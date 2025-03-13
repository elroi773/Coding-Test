function solution(numbers, n) {
    let sum = 0; // 합을 저장할 변수
    
    for (let num of numbers) { // numbers 배열을 순회
        sum += num; // 현재 숫자를 더함
        if (sum > n) return sum; // 합이 n보다 커지면 반환
    }
    
    return sum; // 모든 원소를 더해도 n보다 크지 않으면 반환
}
