package ro.uvt.fi.dp.Interfaces;

import ro.uvt.fi.dp.Objects.Account;

import java.io.Serializable;

public interface Operations extends Serializable {
	double getTotalAmount();

	double getInterest();

	void depose(double amount);

	void retrieve(double amount);
    void transfer(Account account, double amount);
}
