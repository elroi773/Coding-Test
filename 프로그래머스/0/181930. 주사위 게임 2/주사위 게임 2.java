class Solution {
    public int solution(int a, int b, int c) {
        int sum = a + b + c;
        int sumOfSquares = a * a + b * b + c * c;
        int sumOfCubes = a * a * a + b * b * b + c * c * c;

        if (a == b && b == c) {
            return sum * sumOfSquares * sumOfCubes;
        } else if (a == b || b == c || a == c) {
            return sum * sumOfSquares;
        } else {
            return sum;
        }
    }
}
