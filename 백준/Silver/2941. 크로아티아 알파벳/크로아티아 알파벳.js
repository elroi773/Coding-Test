const fs = require("fs");
let s = fs.readFileSync(0, "utf8").trim();

const patterns = ["dz=", "c=", "c-", "d-", "lj", "nj", "s=", "z="];

for (const p of patterns) {
  s = s.split(p).join("*");
}

console.log(s.length);