package ro.uvt.fi.dp.Objects;

import java.util.ArrayList;
import java.util.Collection;

public class Bank {

	private final static int MAX_CLIENTS_NUMBER = 100;
	private Collection<Client> clients;
	private static int clientsNumber=0;
	private String bankCode = null;

	public Bank(String codBanca) {
		this.bankCode = codBanca;
		clients = new ArrayList<>();
	}

	public void addClient(Client c) {
		if(MAX_CLIENTS_NUMBER>clientsNumber)
        {
            clients.add(c);
            clientsNumber++;
        }
	}

	
	public Client getClient(String nume) {
		for (Client c :clients) {
			if (c.getName().equals(nume)) {
				return c;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return "Bank [code=" + bankCode + ", clients=" + clients.toString() + "]";
	}

}
