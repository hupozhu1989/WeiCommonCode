package com.wei.common.threadIO.bio.a;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * 基于TCP协议的Socket通信，实现用户登陆
 * 服务器端
 */
public class Server {
    public static void main(String[] args) {
        try {
            //1.创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
            ServerSocket serverSocket=new ServerSocket(9000);
            Socket socket=null;
            //记录客户端的数量
            int count=0;
            System.out.println("***服务器即将启动，等待客户端的连接***");
            //循环监听等待客户端的连接
            while(true){
                //调用accept()方法开始监听，等待客户端的连接
                socket=serverSocket.accept();
                //创建一个新的线程
                ServerThread serverThread=new ServerThread(socket);
                //启动线程
                serverThread.start();
                
                count++;//统计客户端的数量
                System.out.println("客户端的数量："+count);
                InetAddress address=socket.getInetAddress();
                System.out.println("当前客户端的IP："+address.getHostAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
        传统的HTTP服务器的原理
        1.创建一个ServerSocket，监听并绑定一个端口
        2.一系列客户端来请求这个端口
        3.服务器使用Accept，获得一个来自客户端的Socket连接对象
        4.启动一个新线程处理连接
            ①读Socket，得到字节流
            ②解码协议，得到Http请求对象
            ③处理Http请求，得到一个结果，封装成一个HttpResponse对象
            ④编码协议，将结果序列化字节流
            ⑤写Socket，将字节流发给客户端
        5.继续循环步骤3
     */
}

/*
 * 服务器线程处理类
 */
class ServerThread extends Thread {
    //和本线程相关的Socket
    Socket socket = null;

    public static int count = 0;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    //线程执行的操作，响应客户端的请求
    public void run() {
        InputStreamReader is = null;
        OutputStream os = null;
        BufferedReader br = null;
        try {
            //获取输入流，并读取客户端信息
            is = new InputStreamReader(socket.getInputStream());
            br = new BufferedReader(is);
            os = socket.getOutputStream();

            String line = null;
            while ((line = br.readLine()) != null) {//循环读取客户端的信息
                System.out.println("【客户端】：" + line);
                os.write(("收到消息。。。" + ++count + "\r\n").getBytes("UTF-8"));
                os.flush();//调用flush()方法将缓冲输出
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
                os.close();
                br.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}