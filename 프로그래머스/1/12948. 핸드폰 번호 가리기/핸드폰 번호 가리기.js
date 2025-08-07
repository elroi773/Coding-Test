function solution(phone_number) {
    const length = phone_number.length;
    const hidden = '*'.repeat(length - 4);     // 앞부분 *로 가리기
    const visible = phone_number.slice(-4);    // 마지막 4자리
    return hidden + visible;
}
