package util.scoket;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketTest extends Thread{
    private ServerSocket myServerSocket;
    private final int PORT = 9999;
    public static void main(String[] args){
        ServerSocketTest sst = new ServerSocketTest();
        sst.start();
    }

    public ServerSocketTest(){
        // 初始化一个ServeSocket端  
        try {
            myServerSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        while(true){
            System.out.println("我是服务器，我在9999端口监听....");
            try {
                Socket socket = myServerSocket.accept();
                DataInputStream din = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                String msgIn = din.readUTF();
                System.out.println(msgIn.trim());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
} 
