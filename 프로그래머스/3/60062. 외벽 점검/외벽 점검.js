function solution(n, weak, dist) {
  const m = weak.length;

  // 원형 -> 선형으로 펴기
  const extended = weak.concat(weak.map(w => w + n));

  // dist 순열 생성 준비
  const used = new Array(dist.length).fill(false);
  dist.sort((a, b) => a - b);

  let best = Infinity;

  // segment(길이 m)을 friends(순열 prefix)로 커버 가능한지 검사
  function canCover(segment, friends) {
    let usedCnt = 1;
    let coverageEnd = segment[0] + friends[0];

    for (let i = 0; i < segment.length; i++) {
      if (segment[i] > coverageEnd) {
        usedCnt++;
        if (usedCnt > friends.length) return false;
        coverageEnd = segment[i] + friends[usedCnt - 1];
      }
    }
    return true;
  }

  // dist의 순열을 만들면서, prefix 길이로 최소 친구 수 갱신
  function dfs(depth, perm, segment) {
    if (depth >= best) return; // 이미 best보다 많이 쓰면 가지치기

    if (depth > 0) {
      // 현재까지 뽑은 친구들(perm[0..depth-1])로 커버 가능하면 best 갱신
      if (canCover(segment, perm.slice(0, depth))) {
        best = Math.min(best, depth);
        return; // 더 늘릴 필요 없음(이 segment에서 최소 갱신됨)
      }
    }

    if (depth === dist.length) return;

    let prev = null; // 중복 dist 값이 있을 때 같은 순열 중복 방지
    for (let i = 0; i < dist.length; i++) {
      if (used[i]) continue;
      if (prev !== null && dist[i] === prev) continue;
      prev = dist[i];

      used[i] = true;
      perm[depth] = dist[i];
      dfs(depth + 1, perm, segment);
      used[i] = false;
    }
  }

  // 시작점을 weak의 각 지점으로 잡고 탐색
  for (let start = 0; start < m; start++) {
    const segment = extended.slice(start, start + m);
    dfs(0, new Array(dist.length), segment);
  }

  return best === Infinity ? -1 : best;
}