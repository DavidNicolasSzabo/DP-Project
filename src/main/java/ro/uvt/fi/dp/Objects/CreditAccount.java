package ro.uvt.fi.dp.Objects;

public class CreditAccount extends Account{
    public CreditAccount (String accountCode, double amount, Account.TYPE type){super(accountCode, amount,type);}

    @Override
    public void loan(double loanAmount) {
        if(loanAmount<0)
        {
            System.out.println("You can not loan negative amount!");
            return;
        }
        if(this.getTransaction().getInterest()==0.03 && this.loan<5000 && this.loan+loanAmount<5000)
        {
            this.loan=this.loan+loanAmount;
        } else if (this.getTransaction().getInterest()==0.08 && this.loan<15000 && this.loan+loanAmount<15000) {
            this.loan=this.loan+loanAmount;
        } else if (this.getTransaction().getInterest()==0.01 && this.loan<20000 && this.loan+loanAmount<20000) {
            this.loan=this.loan+loanAmount;
        }else
        {
            System.out.println("You are exceeding your loaning limit");
        }
    }

    @Override
    public void payLoan(double payment) {
        if(payment<0){
            System.out.println("You can not pay negative amount!");
            return;
        }
        if(payment>this.loan)
        {
            this.loan=0;
        }else
        {
            this.loan=this.loan-payment;
        }
    }
}
