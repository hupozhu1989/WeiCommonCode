package com.wei.common.threadIO.mashibing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketIO {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(9090,20);
        System.out.println("step1: new ServerSocket(9090) ");

        while (true) {
            Socket clientSocket = server.accept();  //阻塞1
            System.out.println("step2:client\t" + clientSocket.getPort());

            new Thread(() -> {
                InputStream in = null;
                try {
                    in = clientSocket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    while(true){
                        String line = reader.readLine(); //阻塞2
                        if(null != line){
                            System.out.println(line);
                        }else{
                            clientSocket.close();
                            break;
                        }
                    }
                    System.out.println("客户端断开");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
