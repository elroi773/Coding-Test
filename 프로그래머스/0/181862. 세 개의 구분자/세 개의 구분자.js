function solution(myStr) {
    // "a", "b", "c"를 모두 구분자로 간주하여 분리
    const result = myStr.split(/[abc]/).filter(s => s !== "");

    // 빈 배열이면 ["EMPTY"] 반환
    return result.length === 0 ? ["EMPTY"] : result;
}
