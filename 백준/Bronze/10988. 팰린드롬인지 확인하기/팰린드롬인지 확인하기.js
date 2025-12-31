const fs = require("fs");
const s = fs.readFileSync(0, "utf8").trim();

let l = 0, r = s.length - 1;

while (l < r) {
  if (s[l] !== s[r]) {
    console.log(0);
    process.exit(0);
  }
  l++;
  r--;
}

console.log(1);