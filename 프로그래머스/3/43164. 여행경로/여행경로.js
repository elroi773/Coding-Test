function solution(tickets) {
  // 1) 그래프 구성
  const g = new Map();
  for (const [from, to] of tickets) {
    if (!g.has(from)) g.set(from, []);
    g.get(from).push(to);
  }

  // 2) 사전순 우선 사용을 위해 내림차순 정렬 후 pop()
  for (const [k, arr] of g.entries()) {
    arr.sort((a, b) => (a < b ? 1 : a > b ? -1 : 0)); // 내림차순
  }

  // 3) Hierholzer (스택 기반)
  const stack = ["ICN"];
  const route = [];

  while (stack.length) {
    const cur = stack[stack.length - 1];
    const nexts = g.get(cur);

    if (nexts && nexts.length) {
      stack.push(nexts.pop()); // 가장 작은 목적지부터 사용
    } else {
      route.push(stack.pop()); // 막히면 확정
    }
  }

  return route.reverse();
}