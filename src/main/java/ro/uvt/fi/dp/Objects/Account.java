package ro.uvt.fi.dp.Objects;

import ro.uvt.fi.dp.Loggers.BankLogger;
import ro.uvt.fi.dp.Interfaces.Operations;

import java.io.FileNotFoundException;

public class Account implements Operations {

	public static enum TYPE {
		EUR, RON
	};

	String accountCode = null; //IBAN
	double amount = 0;
    double loan = 0;
	Account.TYPE type = Account.TYPE.RON;
    private BankLogger log;

    {
        try {
            log = BankLogger.getInstance();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected Account(String accountCode, double amount, Account.TYPE type) {
		this.accountCode = accountCode;
		this.type = type;
		depose(amount);
	}

	@Override
	public double getTotalAmount() {

		return amount + amount * getInterest();

	}

	@Override
	public void depose(double amount) {
        if(amount<0)
        {
            System.out.println("Cannot be negative amount!");
            return;
        }
        log.Log("Before deposit:" + Double.toString(this.amount));
		this.amount += amount;
        log.Log("After deposit:" + Double.toString(this.amount));
	}

	@Override
	public void retrieve(double amount) {
        if(amount<0)
        {
            System.out.println("Cannot be negative amount!");
            return;
        }
		this.amount -= amount;
	}

	@Override
	public String toString() {
		if (Account.TYPE.RON == this.type)
			return "Account RON: code=" + accountCode + ", amount=" + amount;
		else
			return "Account EUR: code=" + accountCode + ", amount=" + amount;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public double getInterest() {
		if (Account.TYPE.RON == this.type) {
			if (amount < 500)
				return 0.03;
			else
				return 0.08;
		} else {
			return 0.01;
		}

	}
    public void  loan(double loanAmount){
        if(loanAmount<0)
        {
            System.out.println("You can not loan negative amount!");
            return;
        }
        if(this.getInterest()==0.03 && this.loan<5000 && this.loan+loanAmount<5000)
        {
            this.loan=this.loan+loanAmount;
        } else if (this.getInterest()==0.08 && this.loan<15000 && this.loan+loanAmount<15000) {
            this.loan=this.loan+loanAmount;
        } else if (this.getInterest()==0.01 && this.loan<20000 && this.loan+loanAmount<20000) {
            this.loan=this.loan+loanAmount;
        }else
        {
            System.out.println("You are exceeding your loaning limit");
        }
    }
    public void payLoan(double payment){
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
	@Override
	public void transfer(Account c, double s) {
        if(s<0)
        {
            System.out.println("Cannot be negative amount!");
            return;
        }
		if (Account.TYPE.RON == this.type) {
			c.retrieve(s);
			depose(s);
		}
	}

}
