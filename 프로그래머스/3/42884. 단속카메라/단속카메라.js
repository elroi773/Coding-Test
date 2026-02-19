function solution(routes) {
  // 1) 진출 지점(끝점) 기준 오름차순 정렬
  routes.sort((a, b) => a[1] - b[1]);

  let cameras = 0;
  let camPos = -30001; // 범위(-30000~30000) 밖에서 시작

  // 2) 그리디: 아직 안 걸린 구간을 만나면 그 구간의 끝점에 카메라 설치
  for (const [start, end] of routes) {
    if (camPos < start) {
      cameras++;
      camPos = end;
    }
  }

  return cameras;
}