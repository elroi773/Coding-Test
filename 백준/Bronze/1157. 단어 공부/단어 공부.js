const fs = require("fs");
const s = fs.readFileSync(0, "utf8").trim();

const cnt = Array(26).fill(0);

for (let i = 0; i < s.length; i++) {
  const code = s.charCodeAt(i);
  // 대문자화: a-z면 -32
  const upper = (code >= 97 && code <= 122) ? code - 32 : code;
  cnt[upper - 65]++;
}

let mx = 0;
for (const v of cnt) mx = Math.max(mx, v);

let countMx = 0;
let idx = -1;
for (let i = 0; i < 26; i++) {
  if (cnt[i] === mx) {
    countMx++;
    idx = i;
  }
}

console.log(countMx > 1 ? "?" : String.fromCharCode(65 + idx));