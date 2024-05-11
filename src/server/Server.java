package server;

import interfaces.Executable;
import interfaces.Result;
import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) {
        try {
            // Уведомление о запуске сервера
            System.out.println("Server started...");

            // Принимаем соединение с клиентом
            ServerSocket serverSocket = new ServerSocket(12345);
            Socket clientSocket = serverSocket.accept();

            // Уведомление о подключении клиента
            System.out.println("Client connected...");

            // Создаем объектный поток ввода для приема информации от клиента
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            // Получаем имя class файла задания и сохраняем его в файл
            String classFile = (String) in.readObject();
            classFile = classFile.replaceFirst("client", "server");
            byte[] b = (byte[]) in.readObject();
            FileOutputStream fos = new FileOutputStream(classFile);
            fos.write(b);

            // Получаем объект - задание и вычисляем его
            Executable ex = (Executable) in.readObject();

            // Начало вычислений
            System.out.println("Calculation started...");
            double startTime = System.nanoTime();
            Object output = ex.execute();
            double endTime = System.nanoTime();
            double completionTime = endTime - startTime;
            System.out.println("Calculation completed...");

            // Завершение вычислений
            // Формирование объекта — результата и отправка клиенту
            ResultImpl r = new ResultImpl(output, completionTime);
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            classFile = "D://CrossPlatform2//labar5//out//production//labar5//server//ResultImpl.class";
            out.writeObject(classFile);
            FileInputStream fis = new FileInputStream(classFile);
            byte[] bo = new byte[fis.available()];
            fis.read(bo);
            out.writeObject(bo);
            out.writeObject(r);

            // Закрываем потоки и сокет
            fis.close();
            fos.close();
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
