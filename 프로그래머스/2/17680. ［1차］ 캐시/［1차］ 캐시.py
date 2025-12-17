from collections import OrderedDict

def solution(cacheSize, cities):
    # cacheSize가 0이면 항상 miss
    if cacheSize == 0:
        return 5 * len(cities)

    cache = OrderedDict()
    total = 0

    for city in cities:
        key = city.lower()

        if key in cache:               # cache hit
            total += 1
            cache.move_to_end(key)     # 가장 최근 사용으로 갱신
        else:                          # cache miss
            total += 5
            if len(cache) >= cacheSize:
                cache.popitem(last=False)  # LRU 제거
            cache[key] = True

    return total