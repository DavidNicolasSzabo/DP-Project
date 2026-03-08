package ro.uvt.fi.dp.Objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {



    @Test
    void getClient() {
        Bank bank=new Bank("BCR234");
        assertEquals(null,bank.getClient("Ion"));
    }

    @Test
    void testToString() {
        Bank bank=new Bank("BCR234");
        String word ="null, ";
        assertEquals("Bank [code=BCR234, clients=[]]",bank.toString());
    }
}