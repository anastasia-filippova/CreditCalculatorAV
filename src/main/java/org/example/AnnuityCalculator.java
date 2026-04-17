package org.example;

import java.util.ArrayList;
import java.util.List;

public class AnnuityCalculator implements ICalculator {

    private double principal; 
    private double annualInterestRate;
    private int years;
    private List<Payment> payments;

    /**
     * Устанавливает сумму кредита
     * @param principal - сумма долга
     */
    @Override
    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    /**
     * Устанавливает процентную ставку
     * @param annualInterestRate - процентная ставка
     */
    @Override
    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    /**
     * Метод устанавливает срок кредита в годах
     * @param years - срок выплаты кредита, в годах
     */
    @Override
    public void setYears(int years) {
        this.years = years;
    }

    /**
     * Метод выполняет расчет аннуитетного графика платежей
     */
    @Override
    public void calculatePayments() {
        int months = years * 12;
        double remainingDebt = principal;
        // = annualInterestRate;

        double monthlyRate = annualInterestRate / 12 / 100;
        double monthlyPayment = principal * (monthlyRate * Math.pow(1 + monthlyRate, months)) / (Math.pow(1 + monthlyRate, months) - 1);
        payments = new ArrayList<>();

        for (int month = 1; month <= years * 12; month++) {
            double interestPayment = remainingDebt * monthlyRate;
            double principalPayment = monthlyPayment - interestPayment;
            remainingDebt -= principalPayment;

            payments.add(new Payment(month, principalPayment, interestPayment));
        }
    }

    /**
     * Метод возвращает общую сумму выплат
     * @return общая сумма выплат
     */
    @Override
    public double getTotalPayment() { //реализация получения общих платежей
        return payments.stream().mapToDouble(Payment::getTotalPayment).sum();
    }

    /**
     * Метод возвращает общую сумму процентов
     * @return общая сумма процентов
     */
    @Override
    public double getTotalInterest() { //реализация получения процентов
        return payments.stream().mapToDouble(Payment::getInterestPayment).sum();
    }

    /**
     * Возвращает график платежей
     * @return график платежей
     */
    @Override
    public List<Payment> getPaymentsSchedule() {
                return payments;
    }
}
