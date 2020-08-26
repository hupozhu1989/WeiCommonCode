package com.wei.interview.thread.bio.a;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/*
 * 客户端
 */
public class Client {
    static Socket socket = null;

    public static void main(String[] args) throws IOException {
        //1. 创建Socket对象并且指定服务器的地址和端口号
        socket = new Socket("localhost", 9000);
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();
        try {
            System.err.println("请输入：");
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String line = scanner.nextLine();
                //2.通过输出流向服务器端发送请求信息
                os.write((line + "\r\n").getBytes("UTF-8"));
                os.flush();
                //3.通过输入流获取服务器响应的信息
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String info = br.readLine();
                System.out.println("【服务器】：" + info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            os.close();
            is.close();
            socket.close();
        }
    }
}