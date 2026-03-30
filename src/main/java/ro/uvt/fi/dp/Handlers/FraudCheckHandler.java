package ro.uvt.fi.dp.Handlers;

import ro.uvt.fi.dp.Objects.Account;

public class FraudCheckHandler extends Transaction{
    private final Transaction successor=null;



    public FraudCheckHandler(Account account) {
        super(account);
        setSuccessor(new ApprovalHandler(this.acc));
    }

    @Override
    public void handleRequest(String request) {
            if(request.equals("Denialfraud")){
                System.out.println("Cannot be negative amount!");
            }else if(request.equals("Denialfraud2")){
                System.out.println("Different account monetary system!");
            }else {
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
