package ru.netology;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientTask {
    static final String IP_ADDRESS = "localhost";
    static final int N_PORT = 58001;

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket(IP_ADDRESS, N_PORT);

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scan = new Scanner(System.in)) {

            String scanData;
            while (true) {
                System.out.println("\nВведите номер члена ряда Фибоначчи (или 'end' для завершения работы):");
                scanData = scan.nextLine();
                writer.write(scanData + "\n");
                writer.flush();

                if (scanData.equals("end"))
                    break;

                String serverData;
                while ((serverData = reader.readLine()) == null) {}

                System.out.println("Значение из ряда Фибоначчи: " + serverData);
            }
        }
    }
}
