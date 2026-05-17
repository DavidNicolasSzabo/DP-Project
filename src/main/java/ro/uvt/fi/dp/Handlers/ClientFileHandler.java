package ro.uvt.fi.dp.Handlers;

import ro.uvt.fi.dp.Objects.Client;
import java.io.*;

public class ClientFileHandler {
    public static void saveClient(Client client) {
        String fileName = client.getName().replaceAll("\\s+", "_") + ".ser";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(client);
            System.out.println("Saved client: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Client loadClient(String name) {
        String fileName = name.replaceAll("\\s+", "_") + ".ser";
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Client) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Client file not found: " + fileName);
            return null;
        }
    }
}
