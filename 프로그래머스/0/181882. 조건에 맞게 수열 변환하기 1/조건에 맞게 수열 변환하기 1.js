function solution(arr) {
    return arr.map(x => {
        if (x >= 50 && x % 2 === 0) return x / 2;
        if (x < 50 && x % 2 === 1) return x * 2;
        return x;
    });
}
