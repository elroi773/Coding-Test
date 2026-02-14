function solution(n, computers) {
  let answer = 0;
  const visited = Array(n).fill(false);

  function dfs(cur) {
    visited[cur] = true;
    for (let next = 0; next < n; next++) {
      if (computers[cur][next] === 1 && !visited[next]) {
        dfs(next);
      }
    }
  }

  for (let i = 0; i < n; i++) {
    if (!visited[i]) {
      answer++;
      dfs(i);
    }
  }

  return answer;
}