package ru.netology;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientSpaces {
    static final String IP_ADDRESS = "localhost";
    static final int N_PORT = 58002;

    public static void main(String[] args) throws Exception {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(IP_ADDRESS, N_PORT);
        final SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(inetSocketAddress);

        try (Scanner scan = new Scanner(System.in)) {
            final ByteBuffer inputBuffer = ByteBuffer.allocate(2 << 10);

            String scanData;
            while (true) {
                System.out.println("\nВведите текст (или 'end' для завершения работы):");
                scanData = scan.nextLine();
                if (scanData.equals("end"))
                    break;

                socketChannel.write(ByteBuffer.wrap(scanData.getBytes(StandardCharsets.UTF_8)));

                Thread.sleep(2000);

                int byteCnt = socketChannel.read(inputBuffer);
                System.out.println(new String(inputBuffer.array(), 0, byteCnt, StandardCharsets.UTF_8));
                inputBuffer.clear();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            socketChannel.close();
        }
    }
}
