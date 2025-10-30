function solution(arrayA, arrayB) {
  // 최대공약수(GCD) 구하는 함수
  function gcd(a, b) {
    while (b !== 0) {
      const tmp = a % b;
      a = b;
      b = tmp;
    }
    return a;
  }

  // 배열의 전체 GCD 구하기
  function getGCD(arr) {
    return arr.reduce((acc, cur) => gcd(acc, cur));
  }

  // arr의 모든 원소가 x로 나누어지지 않는지 검사
  function notDivisible(arr, x) {
    return arr.every((n) => n % x !== 0);
  }

  const gcdA = getGCD(arrayA);
  const gcdB = getGCD(arrayB);

  let candidateA = 0;
  let candidateB = 0;

  // 조건 1: A의 모든 수를 나누고, B의 어떤 수도 나누지 않음
  if (notDivisible(arrayB, gcdA)) candidateA = gcdA;

  // 조건 2: B의 모든 수를 나누고, A의 어떤 수도 나누지 않음
  if (notDivisible(arrayA, gcdB)) candidateB = gcdB;

  return Math.max(candidateA, candidateB);
}
