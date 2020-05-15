package com.wei.common.threadIO.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/20 0020
 */
public class NioClient02 {
    public static void main(String[] args) {
        //但客户端单次发送请求
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1",9000);
            OutputStream outputStream = socket.getOutputStream();
            String info = "测试发送数据";
            outputStream.write(info.getBytes("UTF-8"));
            outputStream.write("\r\n".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
