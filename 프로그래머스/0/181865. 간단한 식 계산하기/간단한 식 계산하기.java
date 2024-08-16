class Solution {
    public int solution(String binomial) {
        // Split the binomial string by spaces to extract a, op, and b
        String[] parts = binomial.split(" ");
        
        // Parse the operands and operator
        int a = Integer.parseInt(parts[0]);
        String op = parts[1];
        int b = Integer.parseInt(parts[2]);
        
        // Initialize the result
        int result = 0;
        
        // Perform the operation based on the operator
        switch (op) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
        }
        
        return result;
    }
}
