import re

def solution(word, pages):
    target = word.lower()
    n = len(pages)

    url_re = re.compile(r'<meta[^>]*property="og:url"[^>]*content="([^"]+)"')
    link_re = re.compile(r'<a href="([^"]+)"')

    urls = [""] * n
    out_links = [[] for _ in range(n)]
    base = [0.0] * n
    link_score = [0.0] * n

    url_to_idx = {}

    def count_word_occurrences(html: str, target_lower: str) -> int:
        s = html.lower()
        cnt = 0
        cur = []
        for ch in s:
            if 'a' <= ch <= 'z':
                cur.append(ch)
            else:
                if cur:
                    if ''.join(cur) == target_lower:
                        cnt += 1
                    cur.clear()
        if cur and ''.join(cur) == target_lower:
            cnt += 1
        return cnt

    # 1) parse url / outlinks / base score
    for i, html in enumerate(pages):
        m = url_re.search(html)
        if m:
            urls[i] = m.group(1)
            url_to_idx[urls[i]] = i

        out_links[i] = link_re.findall(html)
        base[i] = float(count_word_occurrences(html, target))

    # 2) distribute link score
    for i in range(n):
        out_cnt = len(out_links[i])
        if out_cnt == 0:
            continue
        share = base[i] / out_cnt
        for to in out_links[i]:
            j = url_to_idx.get(to)
            if j is not None:
                link_score[j] += share

    # 3) best matching score (tie -> smallest index)
    best_idx = 0
    best = base[0] + link_score[0]
    for i in range(1, n):
        s = base[i] + link_score[i]
        if s > best:
            best = s
            best_idx = i

    return best_idx