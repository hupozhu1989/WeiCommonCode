package com.wei.common.threadIO.bio.a;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/*
 * 客户端
 */
public class Client {
    static Socket socket = null;

    public static void main(String[] args) throws IOException {
        socket = new Socket("localhost", 9000);
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();
        try {
            //1.创建客户端Socket，指定服务器地址和端口
            System.err.println("请输入：");
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String line = scanner.nextLine();
                os.write((line + "\r\n").getBytes("UTF-8"));
                os.flush();

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