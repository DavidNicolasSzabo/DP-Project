package ro.uvt.fi.dp.Objects;

import java.io.Serializable;
import ro.uvt.fi.dp.Handlers.BalanceCheckHandler;
import ro.uvt.fi.dp.Handlers.Transaction;
import ro.uvt.fi.dp.Loggers.BankLogger;
import ro.uvt.fi.dp.Interfaces.Operations;

import java.io.FileNotFoundException;
import java.util.Collection;

public abstract class Account implements Serializable {
    private static final long serialVersionUID = 1L;
	public static enum TYPE {
		EUR, RON
	};

	protected String accountCode = null; //IBAN
	protected double amount = 0;
    protected double loan = 0;
    protected Transaction transaction ;




	Account.TYPE type;
    private transient BankLogger log;

    {
        try {
            log = BankLogger.getInstance();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected  Account(String accountCode, double amount, Account.TYPE type) {
		this.accountCode = accountCode;
		this.type = type;
        this.transaction = new Transaction(this){

            @Override
            public void handleRequest(String request) {

            }
        };
        this.transaction.setSuccessor(new BalanceCheckHandler(this));

		this.transaction.depose(amount);

	}
    public double getAmount(){return this.amount;}
    public abstract void  loan(double loanAmount);
    public String getAccountCode() {
        return accountCode;
    }
    public abstract void payLoan(double payment);
	public Transaction getTransaction(){return this.transaction;}
    public  TYPE getType(){return this.type;}
    public void setAmount(double s){this.amount=s;}
    public BankLogger getLog(){return this.log;}

}
