function solution(begin, target, words) {
  if (!words.includes(target)) return 0;

  const n = words.length;
  const dist = Array(n).fill(-1);
  const q = [];
  let head = 0;

  const diffOne = (a, b) => {
    let diff = 0;
    for (let i = 0; i < a.length; i++) {
      if (a[i] !== b[i]) {
        diff++;
        if (diff > 1) return false;
      }
    }
    return diff === 1;
  };

  // begin에서 시작: begin과 1글자 다른 단어들을 dist=1로 큐에 넣기
  for (let i = 0; i < n; i++) {
    if (diffOne(begin, words[i])) {
      dist[i] = 1;
      q.push(i);
    }
  }

  while (head < q.length) {
    const cur = q[head++];

    if (words[cur] === target) return dist[cur];

    for (let nxt = 0; nxt < n; nxt++) {
      if (dist[nxt] === -1 && diffOne(words[cur], words[nxt])) {
        dist[nxt] = dist[cur] + 1;
        q.push(nxt);
      }
    }
  }

  return 0;
}