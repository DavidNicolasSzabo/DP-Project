package ro.uvt.fi.dp.Objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {


    @Test
    void getAccount() {

        Client client=new Client.ClientBuilder("Ion","Str.Viorelelor nr.6").build( Account.TYPE.RON,"1234",200);
        Account account =new SavingsAccount("1234",200, Account.TYPE.RON);
        assertEquals(account.toString(),client.getAccount("1234").toString());
    }

    @Test
    void testToString() {
        Client client=new Client.ClientBuilder("Ion","Str.Viorelelor nr.6").build(Account.TYPE.RON,"1234",200);
        assertEquals("\n\tClient [name=Ion, address=Str.Viorelelor nr.6, acounts=[Account RON: code=1234, amount=200.0]]",client.toString());
    }

    @Test
    void getName() {
        Client client=new Client.ClientBuilder("Ion","Str.Viorelelor nr.6").build(Account.TYPE.RON,"1234",200);
        assertEquals("Ion",client.getName());
    }

//    @Test
//    void setName() {
//        Client client=new Client("Ion","Str.Viorelelor nr.6",Account.TYPE.RON,"1234",200);
//        client.setName("George");
//        assertEquals("George",client.getName());
//    }
}