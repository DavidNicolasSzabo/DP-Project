package ro.uvt.fi.dp.Objects;

import java.lang.invoke.LambdaMetafactory;

public class SavingsAccount extends Account {
    public SavingsAccount(String accountCode, double amount, Account.TYPE type){super(accountCode, amount,type);}

    @Override
    public void loan(double loanAmount) {
        System.out.println("You can not loan on savings account!");
    }

    @Override
    public void payLoan(double payment) {
        System.out.println("You cannot pay loans on savings account!");
    }
}
