package com.wei.interview.thread.bio.b;

import java.io.IOException;
import java.net.Socket;

/**
 * @author weizhenchao
 * @create 2020-04-27-上午 10:28
 */
public class Client {
    public static void main(String[] args) throws IOException{
        //1. 创建Socket对象并且指定服务器的地址和端口号
        Socket socket = new Socket("127.0.0.1",9000);
        //2.通过输出流向服务器端发送请求信息
        socket.getOutputStream().write("Hello Server".getBytes());
        socket.getOutputStream().flush();
        System.out.println("write over, waiting for msg back...");
        //3.通过输入流获取服务器响应的信息
        byte[] bytes = new byte[1024];
        int len = socket.getInputStream().read(bytes);
        System.out.println(new String(bytes,0,len));
    }
}
