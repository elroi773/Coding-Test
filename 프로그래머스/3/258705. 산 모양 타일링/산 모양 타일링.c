#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

int solution(int n, int tops[], size_t tops_len) {
    const int MOD = 10007;

    // free: 현재 정점이 비어있어(아직 매칭 안 됨) 오른쪽과 붙을 수 있는 경우
    // occ : 현재 정점이 이미 왼쪽(또는 위 가지)와 붙어서 점유된 경우
    int free = 1;
    int occ  = 0;

    // 경로 정점 총 2n+1개: v1..v(2n+1)
    // j=2..2n+1 처리
    for (int j = 2; j <= 2 * n + 1; j++) {
        int has_top = 0;
        if (j % 2 == 0) { // 짝수 정점이 tops에 대응 (D_i)
            int idx = j / 2 - 1; // 0-based
            has_top = tops[idx]; // 0 or 1
        }

        int new_free = (free + occ) % MOD; // 단독(정삼각형)으로 두고 다음으로
        int new_occ  = free % MOD;         // 왼쪽과 붙여 마름모(경로 간선 사용)

        if (has_top) {
            // 위 가지와 붙여 마름모(leaf 간선 사용): 현재 정점 상태가 free/occ 모두 가능
            new_occ = (new_occ + free + occ) % MOD;
        }

        free = new_free;
        occ  = new_occ;
    }

    return (free + occ) % MOD;
}