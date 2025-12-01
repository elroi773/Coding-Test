function solution(sequence, k) {
    let start = 0, end = 0;
    let currentSum = sequence[0];
    let minLen = sequence.length;
    let answer = [0, sequence.length - 1];

    while (start < sequence.length && end < sequence.length) {
        if (currentSum < k) {
            end++;
            if (end < sequence.length) currentSum += sequence[end];
        } else if (currentSum > k) {
            currentSum -= sequence[start];
            start++;
        } else { // currentSum === k
            if ((end - start) < minLen) {
                minLen = end - start;
                answer = [start, end];
            }
            currentSum -= sequence[start];
            start++;
        }
    }

    return answer;
}
