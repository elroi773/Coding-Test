function solution(msg) {
  // 1) 사전 초기화: A~Z => 1~26
  const dic = new Map();
  for (let i = 0; i < 26; i++) {
    dic.set(String.fromCharCode(65 + i), i + 1);
  }
  let nextIdx = 27;

  const answer = [];
  let i = 0;

  while (i < msg.length) {
    // 2) 현재 위치에서 사전에 있는 가장 긴 w 찾기
    let j = i + 1;
    while (j <= msg.length && dic.has(msg.slice(i, j))) {
      j++;
    }

    // j는 1칸 더 나간 상태 -> 실제 w = msg[i:j-1]
    const w = msg.slice(i, j - 1);
    answer.push(dic.get(w));

    // 4) 다음 글자가 남아있다면 w+c 사전에 등록
    if (j <= msg.length) {
      const wc = msg.slice(i, j); // w + c
      dic.set(wc, nextIdx++);
    }

    // 3) w만큼 이동
    i += w.length;
  }

  return answer;
}