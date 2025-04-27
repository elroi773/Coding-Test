function solution(myString, pat) {
    // 대소문자 구분 없이 비교해야 하니까 둘 다 소문자로 변환
    myString = myString.toLowerCase();
    pat = pat.toLowerCase();

    // myString 안에 pat이 포함돼 있으면 1, 아니면 0
    return myString.includes(pat) ? 1 : 0;
}
