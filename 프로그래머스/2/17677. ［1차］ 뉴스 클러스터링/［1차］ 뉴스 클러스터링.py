from collections import Counter

def make_multiset(s):
    s = s.lower()
    elements = []
    
    for i in range(len(s) - 1):
        pair = s[i:i+2]
        if pair.isalpha():  # 두 글자 모두 알파벳이면
            elements.append(pair)
    
    return Counter(elements)

def solution(str1, str2):
    A = make_multiset(str1)
    B = make_multiset(str2)
    
    # 둘 다 공집합인 경우
    if not A and not B:
        return 65536
    
    # 교집합과 합집합 크기
    intersection = sum((A & B).values())
    union = sum((A | B).values())
    
    return int((intersection / union) * 65536)
