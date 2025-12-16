function solution(files) {
  // HEAD(대소문자 무시) -> NUMBER(정수) -> 입력순(안정정렬) 기준
  const parsed = files.map((name, idx) => {
    const n = name.length;
    let p = 0;

    // HEAD: 숫자 나오기 전까지
    while (p < n && !(name[p] >= '0' && name[p] <= '9')) p++;
    const head = name.slice(0, p);

    // NUMBER: 최대 5자리 연속 숫자
    const startNum = p;
    let cnt = 0;
    while (p < n && (name[p] >= '0' && name[p] <= '9') && cnt < 5) {
      p++;
      cnt++;
    }
    const numberStr = name.slice(startNum, p);
    const number = parseInt(numberStr, 10);

    return {
      original: name,
      headLower: head.toLowerCase(),
      number,
      idx,
    };
  });

  parsed.sort((a, b) => {
    if (a.headLower < b.headLower) return -1;
    if (a.headLower > b.headLower) return 1;

    if (a.number !== b.number) return a.number - b.number;

    // 같은 HEAD, NUMBER면 입력 순서 유지
    return a.idx - b.idx;
  });

  return parsed.map(x => x.original);
}