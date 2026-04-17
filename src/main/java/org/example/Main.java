package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите сумму долга:");
        double principal = chekDouble(scanner);

        //Добавляем ввод первоначального взноса
        System.out.println("Введите первоначальный взнос");
        double downPayment = chekDouble(scanner);

        //Проверка на правильность введения значений первоначального взноса и суммы кредита
        if (downPayment > principal) {
            System.out.println("Ошибка! Первоначальный взнос не может превышать сумму кредита.");
        }

        System.out.println("Введите срок кредита в годах:");
        int years = chekInt(scanner);

        System.out.println("Введите процентную ставку:");
        double annualInterestRate = chekDouble(scanner);

        System.out.println("Выберите вид платежа (1 - аннуитетный, 2 - дифференцированный):");
        int paymentType;

        while (true) {
            paymentType = chekInt(scanner);
            if (paymentType == 1 || paymentType == 2) {
                break;
            }
            System.out.println("Ошибка! Введено некорректное значение.");
        }

        ICalculator calculator;

        if (paymentType == 1) {
            //Обработка аннуитетного вида платежа при наличии первого взноса
            if (downPayment > 0) {
                AnnuityCalculatorWithDiscount calc = new AnnuityCalculatorWithDiscount();
                calc.setDiscount(downPayment);
                calculator = calc;
            } else {
                calculator = new AnnuityCalculator();
            }
        } else {
            calculator = new DifferentiatedCalculator();
        }

        // Установка переменных
        calculator.setPrincipal(principal);
        calculator.setAnnualInterestRate(annualInterestRate);
        calculator.setYears(years);

        calculator.calculatePayments(); //расчет платежей

        printSchedule(calculator, principal, downPayment);
    }

    private static void printSchedule(ICalculator calculator, double principal, double downPayment) {
        System.out.println("График платежей:");
        for (Payment payment : calculator.getPaymentsSchedule()) {
            System.out.printf("Месяц: %d, Платеж по основному долгу: %.2f, Процентный платеж: %.2f, Общий платеж: %.2f%n",
                    payment.getMonth(), payment.getPrincipalPayment(), payment.getInterestPayment(), payment.getTotalPayment());
        }
        //добавляем расчет переплаты
        double loanAmount = principal - downPayment;
        double overpayment = calculator.getTotalPayment() - loanAmount;

        System.out.printf("Общая сумма выплат: %.2f%n", calculator.getTotalPayment());
        System.out.printf("Общая сумма процентов: %.2f%n", calculator.getTotalInterest());
        System.out.printf("Переплата: %.2f%n", overpayment);
    }

    private static double chekDouble (Scanner scanner) {
        double value;
        while (true) {
            if (scanner.hasNextDouble()) {
                value = scanner.nextDouble();
                if (value >= 0) {
                    break;
                }
            } else {
             scanner.next(); // очищаем ввод
            }
            System.out.println("Ошибка! Введено некорректное значение.");
        }
        return value;
    }

    private static int chekInt(Scanner scanner) {
        int value;
        while (true) {
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                if (value > 0) {
                    break;
                }
            } else {
                scanner.next(); // очищаем ввод
            }
            System.out.println("Ошибка! Введено некорректное значение.");
        }
        return value;
    }

}