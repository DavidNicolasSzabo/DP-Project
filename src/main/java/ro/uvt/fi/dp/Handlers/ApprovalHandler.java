package ro.uvt.fi.dp.Handlers;

import ro.uvt.fi.dp.Objects.Account;

public class ApprovalHandler extends Transaction{
    Transaction successor = null;
    public ApprovalHandler(Account account) {
        super(account);
    }

    @Override
    public void handleRequest(String request) {
        if(request.equals("Approval")){
            System.out.println("Approved!");
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
