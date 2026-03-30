package ro.uvt.fi.dp.Objects;



import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @org.junit.jupiter.api.Test

    void getTotalAmount() {
        Account account = new SavingsAccount("1234",300, Account.TYPE.RON);
        Account account2 = new SavingsAccount("12345",500, Account.TYPE.RON);
        Account account3 = new SavingsAccount("12346",400, Account.TYPE.EUR);
        assertEquals(300+300*0.03,account.getTransaction().getTotalAmount());
        assertEquals(500+500*0.08,account2.getTransaction().getTotalAmount());
        assertEquals(400+400*0.01,account3.getTransaction().getTotalAmount());
    }

    @org.junit.jupiter.api.Test
    void depose() {
        Account account = new SavingsAccount("1234",0, Account.TYPE.RON);
        account.getTransaction().depose(50.0);
        assertEquals(50,account.amount);

    }

    @org.junit.jupiter.api.Test
    void retrieve() {
        Account account = new SavingsAccount("1234",100, Account.TYPE.RON);
        account.getTransaction().retrieve(50);
        assertEquals(50,account.amount);
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        Account account2 = new SavingsAccount("12345",500, Account.TYPE.RON);
        Account account3 = new SavingsAccount("12346",400, Account.TYPE.EUR);
        assertEquals("Account RON: code=12345, amount=540.0",account2.transaction.toString());
        assertEquals("Account EUR: code=12346, amount=404.0",account3.transaction.toString());
    }

    @org.junit.jupiter.api.Test
    void getAccountCode() {
        Account account = new SavingsAccount("1234",0, Account.TYPE.RON);
        assertEquals("1234",account.getAccountCode());
    }

    @org.junit.jupiter.api.Test
    void getInterest() {
        Account account = new SavingsAccount("1234",300, Account.TYPE.RON);
        Account account2 = new SavingsAccount("12345",500, Account.TYPE.RON);
        Account account3 = new SavingsAccount("12346",400, Account.TYPE.EUR);
        assertEquals(0.03,account.getTransaction().getInterest());
        assertEquals(0.08,account2.getTransaction().getInterest());
        assertEquals(0.01,account3.getTransaction().getInterest());
    }

    @org.junit.jupiter.api.Test
    void transfer() {
        Account account = new SavingsAccount("1234",300, Account.TYPE.RON);
        Account account2 = new SavingsAccount("12345",500, Account.TYPE.RON);
        account.getTransaction().transfer(account2,100);
        assertEquals(400,account.amount);
        assertEquals(400,account2.amount);
    }
    @org.junit.jupiter.api.Test
    void loan(){
        Account account = new CreditAccount("1234",300, Account.TYPE.RON);
        Account account2 = new CreditAccount("12345",500, Account.TYPE.RON);
        Account account3 = new SavingsAccount("12346",400, Account.TYPE.EUR);
        Account account4 = new CreditAccount("12346",400, Account.TYPE.EUR);
        account.loan(-50);
        assertEquals(0,account.loan);
        account.loan(4000);
        assertEquals(4000,account.loan);
        account.loan(4000);
        assertEquals(4000,account.loan);
        account2.loan(8000);
        assertEquals(8000,account2.loan);
        account2.loan(8000);
        assertEquals(8000,account2.loan);
        account3.loan(19000);
        assertEquals(0,account3.loan);
        account3.loan(5000);
        assertEquals(0,account3.loan);
        account4.loan(19000);
        assertEquals(19000,account4.loan);
        account4.loan(5000);
        assertEquals(19000,account4.loan);
    }
    @org.junit.jupiter.api.Test
    void payLoan(){
        Account account3 = new CreditAccount("12346",400, Account.TYPE.EUR);
        account3.loan(19000);
        account3.payLoan(-5000);
        assertEquals(19000,account3.loan);
        account3.payLoan(5000);
        assertEquals(14000,account3.loan);
        account3.payLoan(20000);
        assertEquals(0,account3.loan);
    }
}