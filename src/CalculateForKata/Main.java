package CalculateForKata;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        startCalc();
        String line;

        while (!(line = scanner.nextLine()).equalsIgnoreCase("exit")) {

            try {
                Pattern pattern = Pattern.compile("[-+/*]");
                Matcher matcher = pattern.matcher(line);
                String operation = "";

                if (matcher.find()) {
                    operation = matcher.group();
                } else {
                    throw new Exception("Введена не соответствующая возможностям калькулятора операция");
                }

                String[] symbols = line.split("[-+/*]");
                Number firstNumber = NumberService.parseAndValidate(symbols[0].trim());
                Number secondNumber = NumberService.parseAndValidate(symbols[1].trim(), firstNumber.getType());
                String result = Operator.calculate(firstNumber, secondNumber, operation);
                System.out.println("Output: \n" + result);
                System.out.println("Введите данные для вычисления");

            } catch (Exception e) {
                System.out.println(e.getMessage());
                exitCalc();
                break;
            }
        }
        scanner.close();
    }

    private static void startCalc() {
        System.out.println("Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя " +
                "числами: a + b, a - b, a * b, a / b. Данные передаются в одну строку!");
        System.out.println("Для выхода из программы, введите 'exit'");
    }

    private static void exitCalc() {
        System.out.println("Программа заверщена.");
    }
}
