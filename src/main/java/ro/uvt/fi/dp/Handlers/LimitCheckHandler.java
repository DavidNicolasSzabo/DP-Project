package ro.uvt.fi.dp.Handlers;

import ro.uvt.fi.dp.Objects.Account;

public class LimitCheckHandler extends Transaction{
    Transaction successor = new FraudCheckHandler(this.acc);
    public LimitCheckHandler(Account account) {
        super(account);
    }

    @Override
    public void handleRequest(String request) {
        if(request.equals("Denialfunds")){
            System.out.println("Not enough funds!");
        }else{
            if(successor != null){
                successor.handleRequest(request);
            }
        }
    }

    @Override
    public double getTotalAmount() {
        return 0;
    }

    @Override
    public double getInterest() {
        return 0;
    }
}
