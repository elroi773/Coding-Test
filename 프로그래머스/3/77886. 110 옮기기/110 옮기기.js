function solution(s) {
  const answer = new Array(s.length);

  for (let idx = 0; idx < s.length; idx++) {
    const str = s[idx];
    const stack = [];
    let cnt110 = 0;

    // 1) "110" 전부 제거
    for (let i = 0; i < str.length; i++) {
      stack.push(str[i]);

      const n = stack.length;
      if (n >= 3 && stack[n - 3] === '1' && stack[n - 2] === '1' && stack[n - 1] === '0') {
        stack.pop(); stack.pop(); stack.pop();
        cnt110++;
      }
    }

    // 2) 삽입 위치: 남은 문자열에서 마지막 '0' 뒤 (없으면 0)
    let insertIdx = 0;
    for (let i = stack.length - 1; i >= 0; i--) {
      if (stack[i] === '0') {
        insertIdx = i + 1;
        break;
      }
    }

    // 3) 결과 조립
    const mid = "110".repeat(cnt110);
    answer[idx] = stack.slice(0, insertIdx).join('') + mid + stack.slice(insertIdx).join('');
  }

  return answer;
}