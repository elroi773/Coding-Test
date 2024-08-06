class Solution {
    public int solution(int[] num_list) {
        int odd = 0;
        int even = 0;
        
        for (int i = 0; i< num_list.length; i++){
            if ((i+1)%2 == 1){
                odd += num_list[i];
            }else{
                even += num_list[i];
            }
        }
        return Math.max(odd,even);
    }
}