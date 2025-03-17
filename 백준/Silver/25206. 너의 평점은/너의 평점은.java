import java.util.*;
public class Main {
    public static void main (String args[]){
        Scanner sc = new Scanner(System.in);
        Map<String, Double> gradeMap = new HashMap<>();
        gradeMap.put("A+", 4.5);
        gradeMap.put("A0", 4.0);
        gradeMap.put("B+", 3.5);
        gradeMap.put("B0", 3.0);
        gradeMap.put("C+", 2.5);
        gradeMap.put("C0", 2.0);
        gradeMap.put("D+", 1.5);
        gradeMap.put("D0", 1.0);
        gradeMap.put("F", 0.0);
        
        double totalGradePoints = 0.0;
        double totalCredits = 0.0;
        
        for (int i = 0; i < 20; i++) {
            String subject = sc.next();
            double credit = sc.nextDouble();
            String grade = sc.next();
            
            if (!grade.equals("P")) {
                totalGradePoints += credit * gradeMap.get(grade);
                totalCredits += credit;
            }
        }
        
        double gpa = totalGradePoints / totalCredits;
        System.out.printf("%.6f\n", gpa);
    }
}