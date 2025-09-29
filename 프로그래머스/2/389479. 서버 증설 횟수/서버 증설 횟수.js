function solution(players, m, k) {
    let server = new Array(players.length).fill(0);
    let serverCnt = 0;

    for (let i = 0; i < players.length; i++) {
        let n = 0;
        if (players[i] >= m) {
            n = Math.floor(players[i] / m);

            let addServerCnt = 1;
            if (server[i] < n) {
                addServerCnt = n - server[i];
                if (n * m <= players[i] && players[i] < (n + 1) * m) {
                    serverCnt += addServerCnt;
                    for (let j = 0; j < k; j++) {
                        if (i + j < server.length) {
                            server[i + j] += addServerCnt;
                        } else break;
                    }
                }
            }
        }
    }

    return serverCnt;
}
