package ro.uvt.fi.dp.Handlers;

import ro.uvt.fi.dp.Objects.Account;

public class BalanceCheckHandler extends Transaction {
    private final Transaction successor= null;


    public BalanceCheckHandler(Account account) {
        super(account);
        setSuccessor(new LimitCheckHandler(this.acc));
    }

    public void handleRequest(String req) {
            if(req.equals("getTotal")){
                System.out.println(getTotalAmount());
                this.getTotalAmount();
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
        if (Account.TYPE.RON == this.acc.getType()) {
            if (this.acc.getAmount() < 500)
                return 0.03;
            else
                return 0.08;
        } else {
            return 0.01;
        }

    }

    @Override
    public void transfer(Account account, double amount) {

    }



}
