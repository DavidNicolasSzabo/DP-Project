package ro.uvt.fi.dp.Handlers;

import ro.uvt.fi.dp.Interfaces.Operations;
import ro.uvt.fi.dp.Objects.Account;

public abstract class  Transaction implements Operations {
    protected Account acc;
    Transaction successor= new BalanceCheckHandler(this.acc);
    public void setSuccessor(Transaction successor){this.successor = successor;}
    public abstract void handleRequest(String request);
    public Transaction(Account account){this.acc =account;}
    @Override
    public void depose(double amount) {
        if(amount<0)
        {
            this.successor.handleRequest("Denialfraud");
            return;
        }
        this.successor.handleRequest("Approval");
        this.acc.getLog().Log("Before deposit:" + Double.toString(this.successor.getTotalAmount()));
        this.acc.setAmount(this.acc.getAmount() + amount);
        this.acc.getLog().Log("After deposit:" + Double.toString(this.successor.getTotalAmount()));
    }
    @Override
    public double getTotalAmount(){
        this.successor.handleRequest("getTotal");
        return this.successor.getTotalAmount();
    }
    @Override
    public void retrieve(double amount) {
        if(amount<0)
        {
            this.successor.handleRequest("Denialfraud");
            return;
        }
        if(this.getTotalAmount()- amount<0)
        {
            this.successor.handleRequest("Denialfunds");
            return;
        }
        this.successor.handleRequest("Approval");
        this.acc.setAmount(this.acc.getAmount() - amount);
    }

    @Override
    public String toString() {
        if (Account.TYPE.RON == this.acc.getType())
            return "Account RON: code=" + this.acc.getAccountCode() + ", amount=" + this.getTotalAmount();
        else
            return "Account EUR: code=" + this.acc.getAccountCode() + ", amount=" + this.getTotalAmount();
    }

    @Override
    public void transfer(Account c, double s) {
        if(s<0)
        {
            this.successor.handleRequest("Denialfraud");
            return;
        }
        if(this.getTotalAmount()- s<0)
        {
            this.successor.handleRequest("Denialfunds");
            return;
        }
        if (Account.TYPE.RON == this.acc.getType()) {
            this.successor.handleRequest("Approval");
            c.getTransaction().retrieve(s);
            depose(s);
        }else{
            this.successor.handleRequest("Denialfraud2");
        }
    }
    @Override
    public double getInterest() {
        if (Account.TYPE.RON == this.acc.getType()) {
            if (this.acc.getAmount() < 500)
                return 0.03;
            else
                return 0.08;
        } else {
            return 0.01;
        }

    }

}
