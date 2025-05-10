function solution(num_list) {
    let oddStr = '';
    let evenStr = '';
    
    for (let num of num_list) {
        if (num % 2 === 0) {
            evenStr += num;
        } else {
            oddStr += num;
        }
    }
    
    return parseInt(oddStr) + parseInt(evenStr);
}
