import "sort"

type Emp struct {
	a, b    int
	isWanho bool
}

func solution(scores [][]int) int {
	wanhoA, wanhoB := scores[0][0], scores[0][1]
	wanhoSum := wanhoA + wanhoB

	emps := make([]Emp, len(scores))
	for i := range scores {
		emps[i] = Emp{a: scores[i][0], b: scores[i][1], isWanho: i == 0}
	}

	// a 내림차순, b 오름차순 (이게 매우 중요)
	sort.Slice(emps, func(i, j int) bool {
		if emps[i].a != emps[j].a {
			return emps[i].a > emps[j].a
		}
		return emps[i].b < emps[j].b
	})

	rank := 1
	maxB := -1

	for _, e := range emps {
		// 이전에 나온 사람들은 a가 더 크거나 같음(정렬 때문)
		// b가 maxB보다 작으면, a도 더 큰 사람이 존재 + b도 더 큼 => 지배됨
		if e.b < maxB {
			if e.isWanho {
				return -1
			}
			continue
		}

		// 지배되지 않는 사람
		if e.a+e.b > wanhoSum {
			rank++
		}

		if e.b > maxB {
			maxB = e.b
		}
	}

	return rank
}