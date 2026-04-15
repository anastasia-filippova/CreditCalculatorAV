package org.example;

import java.util.List;

/*public interface ICalculator {
    void setPrincipal(double principal); //устанавливает значение основного долга, устанавливает значение той переменнной, которую нам передал пользователь

    void setAnnualInterestRate(double annualInterestRate); //процентная ставка

    void setYears(int years); //количество лет на которое берет кредит

    void calculatePayments(); //расчет платежей по месяцам

    double getTotalPayment(); //общий платеж

    double getTotalInterest(); //общий платеж по процентам, сколько переплачиваем

    List<Payment> getPaymentsSchedule(); //получить график платежей
}*/
public interface ICalculator {
    void setPrincipal(double principal);
    void setAnnualInterestRate(double annualInterestRate);
    void setYears(int years);
    void calculatePayments();
    double getTotalPayment();
    double getTotalInterest();
    List<Payment> getPaymentsSchedule();
}