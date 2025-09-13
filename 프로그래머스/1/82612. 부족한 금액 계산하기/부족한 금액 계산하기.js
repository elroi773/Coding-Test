function solution(price, money, count) {
    // 총 필요한 금액 (등차수열의 합 공식)
    const total = price * count * (count + 1) / 2;
    
    // 부족한 금액
    const needed = total - money;
    
    return needed > 0 ? needed : 0;
}
