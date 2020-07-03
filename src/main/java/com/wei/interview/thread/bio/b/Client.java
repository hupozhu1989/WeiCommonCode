package com.wei.interview.thread.bio.b;

import java.io.IOException;
import java.net.Socket;

/**
 * @author weizhenchao
 * @create 2020-04-27-上午 10:28
 */
public class Client {
    public static void main(String[] args) throws IOException{
        Socket socket = new Socket("127.0.0.1",9000);
        socket.getOutputStream().write("Hello Server".getBytes());
        socket.getOutputStream().flush();
        System.out.println("write over, waiting for msg back...");
        byte[] bytes = new byte[1024];
        int len = socket.getInputStream().read(bytes);
        System.out.println(new String(bytes,0,len));
    }
}
