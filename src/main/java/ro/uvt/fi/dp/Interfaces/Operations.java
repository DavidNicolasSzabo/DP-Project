package ro.uvt.fi.dp.Interfaces;

import ro.uvt.fi.dp.Objects.Account;

public interface Operations {
	double getTotalAmount();

	double getInterest();

	void depose(double amount);

	void retrieve(double amount);
    void transfer(Account account, double amount);
}
