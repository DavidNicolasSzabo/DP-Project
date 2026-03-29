package ro.uvt.fi.dp.Handlers;

import ro.uvt.fi.dp.Objects.Account;

public class BalanceCheckHandler extends Transaction {
    Transaction successor = new LimitCheckHandler(this.acc);
    public BalanceCheckHandler(Account account) {
        super(account);
    }

    public void handleRequest(String req) {
            if(req.equals("getTotal")){
                System.out.println(getTotalAmount());
            }else{
                if(successor != null){
                    successor.handleRequest(req);
                }
            }
    }

    @Override
    public double getTotalAmount() {

        return this.acc.getAmount() + this.acc.getAmount() * getInterest();

    }

    @Override
    public double getInterest() {
        return 0;
    }

    @Override
    public void transfer(Account account, double amount) {

    }



}
