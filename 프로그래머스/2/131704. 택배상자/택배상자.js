function solution(order) {
  const stack = []; // 보조 컨테이너 벨트
  let current = 1;  // 컨테이너에서 꺼낼 다음 상자 번호
  let answer = 0;   // 트럭에 실은 상자 수

  for (let box of order) {
    // 필요한 상자가 나올 때까지 컨테이너에서 꺼내 스택에 저장
    while (current <= order.length && (stack.length === 0 || stack[stack.length - 1] !== box)) {
      stack.push(current);
      current++;
    }

    // 스택의 top이 필요한 상자라면 트럭에 실음
    if (stack.length > 0 && stack[stack.length - 1] === box) {
      stack.pop();
      answer++;
    } else {
      break; // 더 이상 실을 수 없음
    }
  }

  return answer;
}
