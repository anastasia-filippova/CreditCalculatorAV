package org.example;

import java.util.ArrayList;
import java.util.List;

public class AnnuityCalculatorWithDiscount implements IDiscount {
    private double principal;
    private double annualInterestRate;
    private double discount;
    private int years;
    private List<Payment> payments;


    public double getDiscount() {
        return discount;
    }

    /**
     * Устанавливает первоначальный взнос
     * @param discount - первоначальный взнос
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /**
     * Устанавливает сумму кредита с учетом первоначального взноса
     * @param principal - сумма кредита
     */
    @Override
    public void setPrincipal(double principal) { this.principal = principal - discount; }

    /**
     * Устанавливает процентную ставку
     * @param annualInterestRate - процентная ставка
     */
    @Override
    public void setAnnualInterestRate(double annualInterestRate) { this.annualInterestRate = annualInterestRate; }

    /**
     * Устанавливает срок кредита в годах
     * @param years - срок кредита
     */
    @Override
    public void setYears(int years) { this.years = years; }

    /**
     * Метод выполняет расчет аннуитетного графика платежей при наличии первоначального взноса
     */
    @Override
    public void calculatePayments() {
        int months = years * 12;
        double remainingDebt = principal;

        double monthlyRate = annualInterestRate / 12 / 100;
        double monthlyPayment = principal * (monthlyRate * Math.pow(1 + monthlyRate, months)) / (Math.pow(1 + monthlyRate, months) - 1);
        payments = new ArrayList<>();

        for (int month = 1; month <= months; month++) {
            double interestPayment = remainingDebt * monthlyRate;
            double principalPayment = monthlyPayment - interestPayment;
            remainingDebt -= principalPayment;

            if (remainingDebt < 0) {
                remainingDebt = 0;
            }

            payments.add(new Payment(month, principalPayment, interestPayment));
        }
    }

    /**
     * Возвращает общую сумму выплат
     * @return сумма выплат
     */
    @Override
    public double getTotalPayment() {
        return payments.stream().mapToDouble(Payment::getTotalPayment).sum();
    }

    /**
     * Возвращает общую сумму процентов
     * @return сумма процентов
     */
    @Override
    public double getTotalInterest() {return  payments.stream().mapToDouble(Payment::getInterestPayment).sum(); }

    /**
     * Возвращает график платежей
     * @return график платежей
     */
    @Override
    public List<Payment> getPaymentsSchedule() {
        return payments;
    }
}
