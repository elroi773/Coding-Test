class Solution {
    public int solution(int hp) {
        int generalAnts = hp / 5; // 장군개미의 수
        int remainingHp = hp % 5; // 장군개미를 사용하고 남은 체력

        int soldierAnts = remainingHp / 3; // 병정개미의 수
        remainingHp = remainingHp % 3; // 병정개미를 사용하고 남은 체력

        int workerAnts = remainingHp; // 일개미의 수

        return generalAnts + soldierAnts + workerAnts;
    }
}
