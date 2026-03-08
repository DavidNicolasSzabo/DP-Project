package ro.uvt.fi.dp.Objects;

import java.util.ArrayList;
import java.util.Collection;

import ro.uvt.fi.dp.Objects.Account.TYPE;

public class Client {
	public static final int MAX_ACCOUNTS_NO = 5;

	private String name;
	private String address;
	private Collection<Account> accounts;
	private int accountsNo = 0;

	public Client(String nume, String adresa, TYPE tip, String numarCont, double suma) {
		this.name = nume;
		this.address = adresa;
		accounts = new ArrayList<>();
		addAccount(tip, numarCont, suma);
	}

	public void addAccount(TYPE type, String numarCont, double suma) {
		if (MAX_ACCOUNTS_NO > accountsNo){
			Account accnew = new Account(numarCont, suma, type);
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
		return "\n\tClient [name=" + name + ", address=" + address + ", acounts=" + accounts.toString() + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
