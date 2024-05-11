package client;

import interfaces.Result;
import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {

            System.out.println("Connecting to server...");

            Socket client = new Socket("localhost", 12345);

            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

            String classFile = "D://CrossPlatform2//labar5//out//production//labar5//client//JobOne.class";
            out.writeObject(classFile);
            FileInputStream fis = new FileInputStream(classFile);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            out.writeObject(b);

            JobOne aJob = new JobOne(5);

            out.writeObject(aJob);

            System.out.println("Job sent to server...");
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());

            classFile = (String) in.readObject();
            b = (byte[]) in.readObject();
            FileOutputStream fos = new FileOutputStream(classFile);
            fos.write(b);

            Result r = (Result) in.readObject();

            System.out.println("result = " + r.output() + ", time taken = " + r.scoreTime() + "ns");

            // Закрываем потоки и сокет
            fis.close();
            fos.close();
            in.close();
            out.close();
            client.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
