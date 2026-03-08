package ro.uvt.fi.dp.Loggers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BankLogger {
    private static BankLogger logger=null;

    FileOutputStream Logs = new FileOutputStream("BankLogs.txt");

    private BankLogger() throws FileNotFoundException {

    }
    public static synchronized BankLogger getInstance() throws FileNotFoundException {
        if (logger == null)
        logger = new BankLogger();

        return logger;
    }

    FileOutputStream outputStream = null;

    public void Log(String message) {
        try {
            byte[] strToBytes = message.getBytes();
            Logs.write(strToBytes);
        }catch(IOException e)
        {
            System.out.println(e);
        }
    }
}
