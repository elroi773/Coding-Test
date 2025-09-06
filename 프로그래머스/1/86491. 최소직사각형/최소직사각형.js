function solution(sizes) {
    let maxW = 0;
    let maxH = 0;

    for (let [w, h] of sizes) {
        // 항상 큰 쪽을 가로, 작은 쪽을 세로로
        if (w < h) [w, h] = [h, w];
        if (w > maxW) maxW = w;
        if (h > maxH) maxH = h;
    }

    return maxW * maxH;
}

