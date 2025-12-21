function solution(arr) {
    let lcm = arr[0];

    for (let i = 1; i < arr.length; i++) {
        lcm = lcm * arr[i] / gcd(lcm, arr[i]);
    }

    return lcm;
}

// 최대공약수 (유클리드 호제법)
function gcd(a, b) {
    while (b !== 0) {
        let temp = a % b;
        a = b;
        b = temp;
    }
    return a;
}
