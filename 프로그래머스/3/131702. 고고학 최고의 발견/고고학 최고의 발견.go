package main

import "math"

func solution(clockHands [][]int) int {
	n := len(clockHands)
	best := math.MaxInt32

	press := func(b [][]int, r, c, t int) {
		if t == 0 {
			return
		}
		b[r][c] = (b[r][c] + t) & 3
		if r > 0 {
			b[r-1][c] = (b[r-1][c] + t) & 3
		}
		if r < n-1 {
			b[r+1][c] = (b[r+1][c] + t) & 3
		}
		if c > 0 {
			b[r][c-1] = (b[r][c-1] + t) & 3
		}
		if c < n-1 {
			b[r][c+1] = (b[r][c+1] + t) & 3
		}
	}

	cases := 1 << (2 * n) // 4^n

	for mask := 0; mask < cases; mask++ {
		// board 복사
		b := make([][]int, n)
		for i := 0; i < n; i++ {
			b[i] = make([]int, n)
			copy(b[i], clockHands[i])
		}

		cnt := 0
		tmp := mask

		// 1) 첫 행 조작 적용
		for c := 0; c < n; c++ {
			t := tmp & 3 // 0~3
			tmp >>= 2
			cnt += t
			press(b, 0, c, t)
		}

		// 2) 2행~n행: 윗칸을 0으로 만들기 위한 조작은 강제
		for r := 1; r < n; r++ {
			for c := 0; c < n; c++ {
				above := b[r-1][c]
				if above != 0 {
					t := (4 - above) & 3
					cnt += t
					press(b, r, c, t)
				}
			}
		}

		// 3) 마지막 행이 모두 0이면 성공
		ok := true
		for c := 0; c < n; c++ {
			if b[n-1][c] != 0 {
				ok = false
				break
			}
		}
		if ok && cnt < best {
			best = cnt
		}
	}

	return best
}