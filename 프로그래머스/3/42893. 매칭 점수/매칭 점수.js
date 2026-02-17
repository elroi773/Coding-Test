function solution(word, pages) {
  const n = pages.length;
  const target = word.toLowerCase();

  const urlRe = /<meta[^>]*property="og:url"[^>]*content="([^"]+)"/;
  const linkRe = /<a href="([^"]+)"/g;

  const urls = Array(n).fill("");
  const outLinks = Array.from({ length: n }, () => []);
  const base = Array(n).fill(0);
  const linkScore = Array(n).fill(0);

  const urlToIndex = new Map();

  const countWordOccurrences = (htmlLower, targetLower) => {
    let cnt = 0;
    let cur = "";

    for (let i = 0; i < htmlLower.length; i++) {
      const c = htmlLower[i];
      if (c >= "a" && c <= "z") {
        cur += c;
      } else {
        if (cur.length) {
          if (cur === targetLower) cnt++;
          cur = "";
        }
      }
    }
    if (cur.length && cur === targetLower) cnt++;
    return cnt;
  };

  // 1) parse url / outlinks / base score
  for (let i = 0; i < n; i++) {
    const html = pages[i];

    // url
    const um = html.match(urlRe);
    if (um) {
      urls[i] = um[1];
      urlToIndex.set(urls[i], i);
    }

    // outlinks
    linkRe.lastIndex = 0; // reset global regex
    let m;
    while ((m = linkRe.exec(html)) !== null) {
      outLinks[i].push(m[1]);
    }

    // base score (word boundary by non-letters)
    const lower = html.toLowerCase();
    base[i] = countWordOccurrences(lower, target);
  }

  // 2) distribute link score
  for (let i = 0; i < n; i++) {
    const outCnt = outLinks[i].length;
    if (outCnt === 0) continue;

    const share = base[i] / outCnt;
    for (const to of outLinks[i]) {
      if (urlToIndex.has(to)) {
        linkScore[urlToIndex.get(to)] += share;
      }
    }
  }

  // 3) pick best matching score (tie -> smallest index)
  let bestIdx = 0;
  let bestScore = base[0] + linkScore[0];

  for (let i = 1; i < n; i++) {
    const s = base[i] + linkScore[i];
    if (s > bestScore) {
      bestScore = s;
      bestIdx = i;
    }
  }

  return bestIdx;
}