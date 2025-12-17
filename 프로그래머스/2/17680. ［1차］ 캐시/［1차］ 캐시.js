function solution(cacheSize, cities) {
  // cacheSize가 0이면 전부 miss
  if (cacheSize === 0) return cities.length * 5;

  let time = 0;
  const cache = new Map(); // key: 도시(lowercase), value는 의미 없음

  for (const city of cities) {
    const key = city.toLowerCase();

    if (cache.has(key)) { // hit
      time += 1;
      // LRU 갱신: 지웠다가 다시 넣어서 최신으로
      cache.delete(key);
      cache.set(key, true);
    } else { // miss
      time += 5;
      if (cache.size >= cacheSize) {
        // Map의 첫 번째 key가 LRU
        const lruKey = cache.keys().next().value;
        cache.delete(lruKey);
      }
      cache.set(key, true);
    }
  }

  return time;
}