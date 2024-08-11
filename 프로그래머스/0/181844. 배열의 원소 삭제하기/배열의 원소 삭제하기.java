import java.util.*;

class Solution {
    public int[] solution(int[] arr, int[] delete_list) {
        // delete_list의 원소를 빠르게 검색하기 위해 Set에 저장
        Set<Integer> deleteSet = new HashSet<>();
        for (int num : delete_list) {
            deleteSet.add(num);
        }
        
        // 남은 원소들을 저장할 리스트
        List<Integer> result = new ArrayList<>();
        for (int num : arr) {
            // deleteSet에 없는 원소만 result에 추가
            if (!deleteSet.contains(num)) {
                result.add(num);
            }
        }
        
        // 리스트를 배열로 변환하여 반환
        return result.stream().mapToInt(i -> i).toArray();
    }
}
