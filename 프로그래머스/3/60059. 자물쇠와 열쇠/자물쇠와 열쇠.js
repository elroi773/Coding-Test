function solution(key, lock) {
  const m = key.length;
  const n = lock.length;

  const rotate90 = (a) => {
    const len = a.length;
    const r = Array.from({ length: len }, () => Array(len).fill(0));
    for (let i = 0; i < len; i++) {
      for (let j = 0; j < len; j++) {
        r[j][len - 1 - i] = a[i][j];
      }
    }
    return r;
  };

  const pad = m - 1;
  const size = n + pad * 2;

  const board = Array.from({ length: size }, () => Array(size).fill(0));
  // 중앙에 lock 배치
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < n; j++) {
      board[i + pad][j + pad] = lock[i][j];
    }
  }

  const isUnlocked = () => {
    for (let i = 0; i < n; i++) {
      for (let j = 0; j < n; j++) {
        if (board[i + pad][j + pad] !== 1) return false;
      }
    }
    return true;
  };

  let curKey = key;

  for (let rot = 0; rot < 4; rot++) {
    for (let x = 0; x <= size - m; x++) {
      for (let y = 0; y <= size - m; y++) {
        // key 더하기
        for (let i = 0; i < m; i++) {
          for (let j = 0; j < m; j++) {
            board[x + i][y + j] += curKey[i][j];
          }
        }

        if (isUnlocked()) return true;

        // 원복
        for (let i = 0; i < m; i++) {
          for (let j = 0; j < m; j++) {
            board[x + i][y + j] -= curKey[i][j];
          }
        }
      }
    }
    curKey = rotate90(curKey);
  }

  return false;
}