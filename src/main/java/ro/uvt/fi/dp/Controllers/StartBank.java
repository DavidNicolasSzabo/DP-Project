package ro.uvt.fi.dp.Controllers;

import ro.uvt.fi.dp.Commanders.Command;
import ro.uvt.fi.dp.Objects.Account;
import ro.uvt.fi.dp.Objects.Bank;
import ro.uvt.fi.dp.Objects.Client;

import javax.swing.*;

public class StartBank extends JMenuItem implements Command {
    public void execute(){
          Bank bank =  new Bank("13435");
          Client.ClientBuilder client= new Client.ClientBuilder("ion","verde 5");
          bank.addClient(client.build(Account.TYPE.RON,"1234",200));
          System.out.println(bank.toString());
    }

}
