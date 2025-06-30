function solution(my_string, indices) {
  // 1. 제거할 인덱스를 Set으로 만들어 O(1)에 포함 여부를 확인
  const removeSet = new Set(indices);

  // 2. 결과를 담을 배열(혹은 문자열 누적)
  let result = '';

  // 3. my_string을 순회하면서 removeSet에 없는 글자만 이어붙임
  for (let i = 0; i < my_string.length; i++) {
    if (!removeSet.has(i)) {       // 지우지 않을 위치라면
      result += my_string[i];      // 결과에 추가
    }
  }

  return result;
}
