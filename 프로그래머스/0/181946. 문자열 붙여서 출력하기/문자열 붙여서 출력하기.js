const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

let input = [];

rl.on('line', function (line) {
    input = line.split(' ');
    rl.close(); // 입력 받았으면 종료
}).on('close', function () {
    const str1 = input[0];
    const str2 = input[1];
    console.log(str1 + str2); // 이어붙여서 출력
});
