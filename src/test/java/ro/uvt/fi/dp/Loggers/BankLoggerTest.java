package ro.uvt.fi.dp.Loggers;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class BankLoggerTest {

    @Test
    void getInstance() throws FileNotFoundException {
        BankLogger log=BankLogger.getInstance();
    }

    @Test
    void log() throws FileNotFoundException {
        BankLogger log=BankLogger.getInstance();
        log.Log("Ok");
    }
}