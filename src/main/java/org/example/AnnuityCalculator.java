package org.example;

import java.util.ArrayList;
import java.util.List;

public class AnnuityCalculator implements ICalculator {
    private double principal; //сумма долга
    private double annualInterestRate; //ежегодная процентная ставка
    private int years;
    private List<Payment> payments; //график платежей

    @Override
    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    @Override
    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    @Override
    public void setYears(int years) {
        this.years = years;
    }

    @Override
    public void calculatePayments() {
        double monthlyRate = annualInterestRate / 12 / 100;
        double monthlyPayment = principal * (monthlyRate * Math.pow(1 + monthlyRate, years * 12)) / (Math.pow(1 + monthlyRate, years * 12) - 1); //Math.pow(n, m) - возводит в степень m число n
        payments = new ArrayList<>();

        for (int month = 1; month <= years * 12; month++) {
            double interestPayment = principal * monthlyRate;
            double principalPayment = monthlyPayment - interestPayment;
            principal -= principalPayment;

            payments.add(new Payment(month, principalPayment, interestPayment));
        }
    }

    @Override
    public double getTotalPayment() { //реализация получение общих платежей
        return payments.stream().mapToDouble(Payment::getTotalPayment).sum(); //получение из метода getTotalPayment платежи и суммирование и получим сколько мы заплатили исходя из суммы основного долга
    }

    @Override
    public double getTotalInterest() { //реализация получение процентов
        return payments.stream().mapToDouble(Payment::getInterestPayment).sum();
    }

    @Override
    public List<Payment> getPaymentsSchedule() { //плучить весь список платежей, который мы рассчитали
        return payments;
    }
}
