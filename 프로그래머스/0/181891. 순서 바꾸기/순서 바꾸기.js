function solution(num_list, n) {
    const front = num_list.slice(0, n);      // n번째까지의 원소들 (0 ~ n-1)
    const back = num_list.slice(n);          // n번째 이후의 원소들 (n ~ 끝까지)
    return back.concat(front);               // back + front 순서로 결합
}
