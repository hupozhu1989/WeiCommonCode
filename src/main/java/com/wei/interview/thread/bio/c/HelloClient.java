package com.wei.interview.thread.bio.c;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HelloClient {
    //https://mp.weixin.qq.com/s?__biz=MzIxNDM1NjAyMQ==&mid=2247483754&idx=1&sn=83be81dc7b612c22269998146d5a6ef7&chksm=97a99830a0de11269d577d6167598ded201647a2fb23b96bebf3c39b826aeb36eb8f3b805d33&scene=126&sessionid=1598403556&key=0ac52f944ed0d7b1e74b3d9c4222016432196c284b25d4327738d16e291cf96b265e089f5876080a72e7e36e7c87062dbdcd2e4ad3e5ad626e704c6c380f423822680e949d67c5a9259c189644b2faf01765b71ff7e374274ad7607a4c17d4fac031ed986d05f38165e81767eb19c08228f05242511e0b13eab63e994009f70b&ascene=1&uin=MTAxMjMxNzQwMA%3D%3D&devicetype=Windows+7+x64&version=62090529&lang=zh_CN&exportkey=AZSGvTairvDbWQjP2nfgdMs%3D&pass_ticket=n24jzE1yi4CrNA6BIMRFZYIYmy%2FeKv3NCRBPyovvGiTkrufYdFBSQZI88KqKXADz
    private static final Logger logger = LoggerFactory.getLogger(HelloClient.class);

    public Object send(Message message, String host, int port) {
        //1. 创建Socket对象并且指定服务器的地址和端口号
        try (Socket socket = new Socket(host, port)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            //2.通过输出流向服务器端发送请求信息
            objectOutputStream.writeObject(message);
            //3.通过输入流获取服务器响应的信息
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.error("occur exception:", e);
        }
        return null;
    }

    public static void main(String[] args) {
        HelloClient helloClient = new HelloClient();
        Message message = (Message)helloClient.send(new Message("content from client"), "127.0.0.1", 9000);
        System.out.println("client receive message:" + message.getContent());
    }
}