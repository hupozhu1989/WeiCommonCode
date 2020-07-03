package com.wei.interview.thread.bio.b;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author weizhenchao
 * @create 2020-04-27-上午 10:12
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress("127.0.0.1",9000));
        while (true){
            Socket socket = ss.accept();//阻塞方法

            new Thread(()->{
                handle(socket);
            }).start();
        }

    }
    static void handle(Socket socket){
        try {
            byte[] bytes = new byte[1024];
            int len = socket.getInputStream().read(bytes);//阻塞方法
            System.out.println(new java.lang.String(bytes, 0, len));

            socket.getOutputStream().write(bytes,0,len);//阻塞方法
            socket.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
