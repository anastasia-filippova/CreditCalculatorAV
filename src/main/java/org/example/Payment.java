package org.example;

public class Payment {
    private final int month;
    private final double principalPayment;
    private final double interestPayment;
    private final double totalPayment;

    /**
     * Создает объект ежемесячного платежа.
     *
     * @param month номер месяца
     * @param principalPayment платеж по основному долгу
     * @param interestPayment процентный платеж
     */
    public Payment(int month, double principalPayment, double interestPayment) {
        this.month = month;
        this.principalPayment = principalPayment;
        this.interestPayment = interestPayment;
        this.totalPayment = principalPayment + interestPayment;
    }

    /**
     * Возвращает номер месяца
     * @return номер месяца
     */
    public int getMonth() {
        return month;
    }

    /**
     * Возвращает сумму платежа по основному долгу
     * @return платеж по основному долгу
     */
    public double getPrincipalPayment() {
        return principalPayment;
    }

    /**
     * Возвращает сумму процентного платежа
     * @return процентный платеж
     */
    public double getInterestPayment() {
        return interestPayment;
    }

    /**
     * Возвращает общий ежемесячный платеж
     * @return общая сумма платежа
     */
    public double getTotalPayment() {
        return totalPayment;
    }
}
