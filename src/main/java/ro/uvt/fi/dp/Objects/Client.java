package ro.uvt.fi.dp.Objects;

import java.util.ArrayList;
import java.util.Collection;

import ro.uvt.fi.dp.Objects.Account.TYPE;

public class Client {
	public static final int MAX_ACCOUNTS_NO = 5;

	private  final String name;
	private final String address;
	private final Collection<Account> accounts;
	private  int accountsNo;
    private final String phoneNumber;
    private Client(ClientBuilder builder,TYPE tip,String numarCont,double suma){
        this.name = builder.name;
        this.address = builder.address;
        this.accountsNo = builder.accountsNo;
        this.accounts = builder.accounts;
        this.phoneNumber = builder.phoneNumber;
        addAccount(tip, numarCont, suma);

    }
    public static class ClientBuilder{
        private String name;
        private String address;
        private Collection<Account> accounts;
        private int accountsNo = 0;
        private String phoneNumber;
        public ClientBuilder(String name,String address){
            this.name=name;
            this.address=address;
            this.accounts = new ArrayList<>();
        }
        public ClientBuilder phoneNumber(String number){
            this.phoneNumber=number;
            return this;
        }
        public Client build(TYPE tip,String numarCont,double suma){
            return new Client(this,tip,numarCont,suma);
        }
    }


	public void addAccount(TYPE type, String numarCont, double suma) {
		if (MAX_ACCOUNTS_NO > accountsNo){
			Account accnew = new SavingsAccount(numarCont, suma, type);
            accounts.add(accnew);
            accountsNo++;
        }
	}

	public Account getAccount(String accountCode) {
		for (Account currentAccount : accounts)
			if (currentAccount.getAccountCode().equals(accountCode)) {
				return currentAccount;
			}
		return null;
	}

	@Override
	public String toString() {
        Collection<Account> accs =this.getAccounts();
        String message ="";
        message= message + "\n\tClient [name=" + name + ", address=" + address + ", acounts=[";
        for(Account a : accs) {
            message = message + a.getTransaction().toString();}
        message = message + "]]";
		return message;
	}

	public String getName() {
		return name;
	}
    public Collection<Account> getAccounts(){return this.accounts;}
    public Client getClient(){
        return new
                ClientBuilder("John Doe","Fake address")
                .phoneNumber("1234343254")
                .build(TYPE.RON,"fake acc number",0.0);
    }
//	public void setName(String name) {
//		this.name = name;
//	}
}
