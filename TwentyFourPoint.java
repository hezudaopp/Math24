import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


public class TwentyFourPoint {
    final static char[] OPERATORS = new char[] {'+', '-', '*', '/'};

    final static BigDecimal TWENTY_FOUR = BigDecimal.valueOf(24.0d);
    final static BigDecimal ZERO = BigDecimal.valueOf(0.0d);

    public static void main(String[] args) {
//        int[] digits = randomInt();
        int[] digits = new int[]{4, 4, 12, 9};
        for (int digit : digits) {
            System.out.print(digit + " ");
        }
        System.out.println();
        System.out.println();

        List<int[]> listOfPermutation = fullPermute(digits);
        int i = 0;
        for (int[] permutation : listOfPermutation) {
            List<Double> operands = new LinkedList<>();
            for (int digit : permutation) {
                operands.add((double) digit);
            }
            List<String> solutions = calculateTwentyFour(operands);
            for (String equation : solutions) {
                i++;
                System.out.println(i + ": " + equation);
            }
        }
    }

    private static List<String> calculateTwentyFour(List<Double> operands) {
        Stack<String> operators = new Stack<>();
        List<Stack<String>> solutions = new ArrayList<>();
        calculateRecursively(operands, operators, solutions);
        List<String> result = new ArrayList<>();
        for (Stack<String> solution : solutions) {
            StringBuilder sb = new StringBuilder();
            for (String s : solution) {
                sb.append(s);
            }
            result.add(sb.toString());
        }
        return result;
    }

    private static void calculateRecursively(List<Double> operands, Stack<String> operators, List<Stack<String>> solutions) {
        if (operands.size() == 1) {
            Double result = operands.get(0);
            if (result.equals(Double.NaN)) {
                return;
            }
            if (TWENTY_FOUR.equals(BigDecimal.valueOf(result))) {
                solutions.add((Stack<String>) operators.clone());
            }
            return;
        }
        for (int i = 0; i < operands.size() - 1; i++) {
            for (char operator : OPERATORS) {
                double operand1 = operands.remove(i);
                double operand2 = operands.remove(i);
                double tmp = calculate(operator, operand1, operand2);
                operands.add(i, tmp);
                operators.push("" + operand1 + operator + operand2 + "=" + tmp + " ");
                calculateRecursively(operands, operators, solutions);
                operators.pop();
                operands.remove(i);
                operands.add(i, operand1);
                operands.add(i + 1, operand2);
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
        } else if (operator == '/' && operand2 != 0) {
            return operand1 / operand2;
        } else {
            return Double.NaN;
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
