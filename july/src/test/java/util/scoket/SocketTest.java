package util.scoket;

import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


//https://developer.aliyun.com/article/12357
/**
 * @ClassName: SocketTest
 * @Description: 测试Socket中，流关闭后，socket是否关闭？是否可重开流？输出缓存区的数据是发送出去，还是丢弃？ 
 * @author 慢跑学Android
 * @date 2011-11-12 上午11:15:21 
 *
 */
public class SocketTest {
    Socket mySocket;
    DataOutputStream dout;
   /* public static void main(String[] args){
        new SocketTest();
    }*/

   /* public SocketTest(){
        // 输出流关闭的测试一：socket关闭吗？ 
        System.out.println("########### test1 ##############");
        test1();
        // 输出流关闭测试二：该流是否可以重新开启？ 
        System.out.println("########### test2 ##############");
        test2();
        // 输出流关闭测试三：输出缓冲区里的数据是丢弃，还是发送？  
        System.out.println("########### test3 ##############");
        test3();
    }*/

    
    @Test
    public void test1() {
        // 输出流关闭的测试一：socket关闭吗？  
        System.out.println("\n****2种方式关闭输出流，Socket是否关闭？***\n");
        try {
            mySocket = new Socket("0.0.0.0",9999);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            dout = new DataOutputStream(new BufferedOutputStream(mySocket.getOutputStream()));
            //下面这一句主要是用来证明socket确实处于开启状态  
            System.out.println("输出流刚打开,Socket是否关闭？" + mySocket.isClosed());
            mySocket.shutdownOutput();
            System.out.println("使用shutdownOutput关闭输出流，Socket是否关闭？" + mySocket.isClosed());
            dout.close();
            System.out.println("使用close关闭输出流，Socket是否关闭？" + mySocket.isClosed());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        // 输出流关闭测试二：使用shutdownOutputStream后，输出流是否可以重新开启？  
        System.out.println("\n****使用shutdownOutputStream后，输出流是否可以重新开启？***\n");
        try {
            mySocket = new Socket("0.0.0.0",9999);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            dout = new DataOutputStream(new BufferedOutputStream(mySocket.getOutputStream()));
            mySocket.shutdownOutput();
            // 重开输出流  
            dout = new DataOutputStream(mySocket.getOutputStream());
            dout.writeUTF("是否允许我重开？");
            // 清空输出缓存，确保当dout通道没问题时，消息可以到达服务器  
            dout.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                mySocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test3(){
        // 输出流关闭测试三：输出缓冲区里的数据是丢弃，还是发送？  
        System.out.println("\n***输出缓冲区里的数据是丢弃，还是发送？****\n");
        try {
            mySocket = new Socket("0.0.0.0",9999);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            dout = new DataOutputStream(new BufferedOutputStream(mySocket.getOutputStream()));
            dout.writeUTF("shutdownOutput后，数据发得得出去吗？");
            mySocket.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}  