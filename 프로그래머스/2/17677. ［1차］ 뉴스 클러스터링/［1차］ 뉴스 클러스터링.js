function solution(str1, str2) {

    const makeMap = (str) => {
        const map = new Map();
        str = str.toLowerCase();

        for (let i = 0; i < str.length - 1; i++) {
            const a = str[i];
            const b = str[i + 1];

            if (a >= 'a' && a <= 'z' && b >= 'a' && b <= 'z') {
                const key = a + b;
                map.set(key, (map.get(key) || 0) + 1);
            }
        }
        return map;
    };

    const map1 = makeMap(str1);
    const map2 = makeMap(str2);

    // 둘 다 공집합이면 유사도 1
    if (map1.size === 0 && map2.size === 0) {
        return 65536;
    }

    let intersection = 0;
    let union = 0;

    const keys = new Set([...map1.keys(), ...map2.keys()]);

    for (const key of keys) {
        const cnt1 = map1.get(key) || 0;
        const cnt2 = map2.get(key) || 0;

        intersection += Math.min(cnt1, cnt2);
        union += Math.max(cnt1, cnt2);
    }

    return Math.floor((intersection / union) * 65536);
}
