function solution(lines) {
    const intervals = []; // [startMs, endMs]

    for (const line of lines) {
        const parts = line.split(" ");
        const timeStr = parts[1];
        const durStr = parts[2];

        const endMs = parseTimeToMs(timeStr);
        const durMs = parseDurationToMs(durStr);
        const startMs = endMs - durMs + 1; // 시작/끝 포함

        intervals.push([startMs, endMs]);
    }

    let answer = 0;

    // 각 로그의 시작/끝 시점을 기준으로 1초 구간 [t, t+999] 검사
    for (const [start, end] of intervals) {
        const candidates = [start, end];

        for (const windowStart of candidates) {
            const windowEnd = windowStart + 999;
            let count = 0;

            for (const [s, e] of intervals) {
                // 구간 [s, e] 와 [windowStart, windowEnd]가 겹치면 카운트
                if (e >= windowStart && s <= windowEnd) {
                    count++;
                }
            }

            if (count > answer) answer = count;
        }
    }

    return answer;
}

function parseTimeToMs(timeStr) {
    // "hh:mm:ss.sss"
    const h = Number(timeStr.slice(0, 2));
    const m = Number(timeStr.slice(3, 5));
    const s = Number(timeStr.slice(6, 8));
    const ms = Number(timeStr.slice(9, 12));

    return ((h * 3600 + m * 60 + s) * 1000) + ms;
}

function parseDurationToMs(durStr) {
    // "2s", "2.0s", "0.351s"
    const t = durStr.slice(0, -1); // remove 's'

    if (!t.includes(".")) {
        return Number(t) * 1000;
    }

    const [secStr, fracStrRaw] = t.split(".");
    let fracStr = fracStrRaw;

    while (fracStr.length < 3) fracStr += "0";
    if (fracStr.length > 3) fracStr = fracStr.slice(0, 3);

    return Number(secStr) * 1000 + Number(fracStr);
}