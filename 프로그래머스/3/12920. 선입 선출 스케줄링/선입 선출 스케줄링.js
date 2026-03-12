function solution(n, cores) {
    const m = cores.length;
    if (n <= m) return n;

    const countJobs = (time) => {
        let total = 0;
        for (const core of cores) {
            total += Math.floor(time / core) + 1;
        }
        return total;
    };

    let left = 0;
    let right = Math.max(...cores) * n;

    while (left < right) {
        const mid = Math.floor((left + right) / 2);
        if (countJobs(mid) >= n) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }

    const time = left;
    let done = countJobs(time - 1);

    for (let i = 0; i < m; i++) {
        if (time % cores[i] === 0) {
            done++;
            if (done === n) return i + 1;
        }
    }

    return -1;
}