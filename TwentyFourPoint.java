import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class TwentyFourPoint {
    public static void main(String[] args) {
        int[] digits = randomInt();
        for (int digit : digits) {
            System.out.print(digit + " ");
        }
        System.out.println();
        System.out.println();

        List<int[]> listOfPermutation = fullPermute(digits);
        char[] operators = new char[] {'+', '-', '*', '/'};
        for (int[] permutation : listOfPermutation) {
            for (char operator1 : operators) {
                double result1 = calculate(operator1, permutation[0], permutation[1]);
                for (char operator2 : operators) {
                    double result2 = calculate(operator2, result1, permutation[2]);
                    for (char operator3 : operators ) {
                        double result3 = calculate(operator3, result2, permutation[3]);
                        BigDecimal result = new BigDecimal(result3);
                        if (result.equals(new BigDecimal(24))) {
                            System.out.println("(((" + permutation[0] + " " + operator1 + " " + permutation[1] + ") " + operator2 + " " + permutation[2] + ") " + operator3  + " " + permutation[3] + ") = 24");
                        }
                    }
                }
            }
        }

    }

    private static double calculate(char operator, double operand1, double operand2) {
        if (operator == '+') {
            return operand1 + operand2;
        } else if (operator == '-') {
            return operand1 - operand2;
        } else if (operator == '*') {
            return operand1 * operand2;
        } else if (operator == '/') {
            return operand1 / operand2;
        } else {
            return Double.NEGATIVE_INFINITY;
        }
    }

    private static int[] randomInt() {
        int[] digits = new int[4];
        for (int i = 0; i < digits.length; i++) {
            digits[i] = (int) (Math.random() * 13) + 1;
        }
        return digits;
    }

    private static List<int[]> fullPermute(int[] numbers) {
        List<int[]> result = new ArrayList<>();
        permute(numbers, 0, result);
        return result;
    }

    private static void permute(int[] numbers, int startIndex, List<int[]> result) {
        if (startIndex == numbers.length - 1) {
            result.add(numbers.clone());
            return;
        }
        for (int i = startIndex; i < numbers.length; i++) {
            swap(numbers, startIndex, i);
            permute(numbers, startIndex + 1, result);
            swap(numbers, startIndex, i);
        }
    }

    private static void swap(int[] numbers, int p, int q) {
        if (p == q) {
            return;
        }
        int tmp = numbers[p];
        numbers[p] = numbers[q];
        numbers[q] = tmp;
    }
}
