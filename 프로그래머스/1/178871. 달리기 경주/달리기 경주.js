function solution(players, callings) {
    // 이름 -> 현재 등수 (index) 맵
    const nameToIndex = new Map();
    for (let i = 0; i < players.length; i++) {
        nameToIndex.set(players[i], i);
    }

    for (const name of callings) {
        const idx = nameToIndex.get(name);
        const prevPlayer = players[idx - 1];

        // swap players[idx] <-> players[idx - 1]
        [players[idx], players[idx - 1]] = [players[idx - 1], players[idx]];

        // update nameToIndex map
        nameToIndex.set(name, idx - 1);
        nameToIndex.set(prevPlayer, idx);
    }

    return players;
}
