package org.example;

import java.util.ArrayList;
import java.util.List;

public class DifferentiatedCalculator implements ICalculator{
    private double principal;
    private double annualInterestRate;
    private int years;
    private List<Payment> payments;

    /**
     * Устанавливает сумму кредита
     * @param principal - сумма кредита
     */
    @Override
    public void setPrincipal(double principal) { this.principal = principal; }

    /**
     * Устанавливает процентную ставку
     * @param annualInterestRate - процентная ставка
     */
    @Override
    public void setAnnualInterestRate(double annualInterestRate) { this.annualInterestRate = annualInterestRate; }

    /**
     * Устанавливает срок кредита в годах
     * @param years
     */
    @Override
    public void setYears(int years) { this.years = years; }

    /**
     * Выполняет расчет дифференцированного графика платежей
     */
    @Override
    public void calculatePayments() {
        double monthlyRate = annualInterestRate / 12 / 100;
        double restOfDebt = principal;
        payments = new ArrayList<>();

        for (int month = 1; month <= years * 12; month++) {
            double principalPayment = principal / (years * 12);
            double interestPayment = restOfDebt * monthlyRate;
            restOfDebt -= principalPayment;

            payments.add(new Payment(month, principalPayment, interestPayment));
        }
    }

    /**
     * Возвращает общую сумму выплат.
     * @return общую сумму выплат
     */
    @Override
    public double getTotalPayment() {
        return payments.stream().mapToDouble(Payment::getTotalPayment).sum();
    }

    /**
     * Возвращает общую сумму процентов
     * @return общая сумма процентов
     */
    @Override
    public double getTotalInterest() {
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
