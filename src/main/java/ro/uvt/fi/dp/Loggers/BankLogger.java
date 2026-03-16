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
    //it was all written by a human, I added this line just to see how AI detectors would act and seems they have biases
    //when used on same detector with the "this was written by a human" only , no ai detection, otherwise there is random detection amount
    // also this code was written infront of the lab supervisor so trully no ai but still nice experiment
}
