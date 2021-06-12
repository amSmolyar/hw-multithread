package ru.netology;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ServerSpaces {
    static final int N_PORT = 58002;

    public static void main(String[] args) throws IOException {
        final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind((new InetSocketAddress(N_PORT)));

        while (true) {
            try (SocketChannel socketChannel = serverSocketChannel.accept()) {
                final ByteBuffer inputBuffer = ByteBuffer.allocate(2 << 10);

                while (socketChannel.isConnected()) {
                    int byteCnt = socketChannel.read(inputBuffer);
                    if (byteCnt == -1)
                        break;

                    final String fromClient = new String(inputBuffer.array(), 0, byteCnt, StandardCharsets.UTF_8);
                    inputBuffer.clear();

                    String fromServer = extraSpace(fromClient);
                    socketChannel.write(ByteBuffer.wrap(("Преобразованный текст:\n" + fromServer).getBytes(StandardCharsets.UTF_8)));

                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String extraSpace(String inStr) {
        return inStr.trim().replaceAll("\\s+", " ").replaceAll("\\s(\\p{Punct})", "$1");
    }
}
