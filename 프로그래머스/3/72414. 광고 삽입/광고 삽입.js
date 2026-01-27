function solution(play_time, adv_time, logs) {
  const playSec = toSec(play_time);
  const advSec = toSec(adv_time);

  if (advSec >= playSec) return "00:00:00";

  // diff[t] = t초에서 시청자 수 변화량
  const diff = new Array(playSec + 2).fill(0);

  // [start, end) 로 처리
  for (const log of logs) {
    const dash = log.indexOf("-");
    const s = toSec(log.slice(0, dash));
    const e = toSec(log.slice(dash + 1));
    diff[s] += 1;
    diff[e] -= 1;
  }

  // prefixWatch[t] = 0초 ~ (t-1)초까지 누적 재생시간(초 합)
  const prefixWatch = new Array(playSec + 2).fill(0);
  let viewers = 0;

  for (let t = 0; t < playSec; t++) {
    viewers += diff[t];
    prefixWatch[t + 1] = prefixWatch[t] + viewers;
  }

  let bestStart = 0;
  let bestValue = -1;

  for (let start = 0; start <= playSec - advSec; start++) {
    const end = start + advSec;
    const value = prefixWatch[end] - prefixWatch[start];
    if (value > bestValue) {
      bestValue = value;
      bestStart = start;
    }
    // value == bestValue면 더 빠른 bestStart 유지 (갱신 X)
  }

  return toTime(bestStart);
}

function toSec(t) {
  const h = (t.charCodeAt(0) - 48) * 10 + (t.charCodeAt(1) - 48);
  const m = (t.charCodeAt(3) - 48) * 10 + (t.charCodeAt(4) - 48);
  const s = (t.charCodeAt(6) - 48) * 10 + (t.charCodeAt(7) - 48);
  return h * 3600 + m * 60 + s;
}

function toTime(sec) {
  const h = Math.floor(sec / 3600);
  const m = Math.floor((sec % 3600) / 60);
  const s = sec % 60;
  return (
    String(h).padStart(2, "0") +
    ":" +
    String(m).padStart(2, "0") +
    ":" +
    String(s).padStart(2, "0")
  );
}