package ru.netology;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerTask {
    static final int N_PORT = 58001;

    public static void main(String[] args) throws IOException {
        int nFibonacci;
        int cntFibonacci;
        List<Integer> listFibonacci = new ArrayList<>();
        listFibonacci.add(0);
        listFibonacci.add(1);

        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(N_PORT);
                 Socket socket = serverSocket.accept();
                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                String fromClient;
                while ((fromClient = reader.readLine()) != null) {
                    if (fromClient.equals("end"))
                        break;

                    try {
                        cntFibonacci = Integer.parseInt(fromClient);
                        nFibonacci = getFibonacci(listFibonacci, cntFibonacci);
                        writer.write(nFibonacci + "\n");
                        writer.flush();
                    } catch (NumberFormatException e) {
                        System.out.println(e.getMessage());
                        writer.write("Неправильный формат входных данных" + "\n");
                        writer.flush();
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int getFibonacci(List<Integer> list, int cntFibonacci) {
        int first;
        int second;
        while (cntFibonacci > (list.size() - 1)) {
            first = list.get(list.size() - 2);
            second = list.get(list.size() - 1);
            list.add(first + second);
        }
        return list.get(cntFibonacci);

    }
}
