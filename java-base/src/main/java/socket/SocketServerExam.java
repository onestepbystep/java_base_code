package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author:hmh
 * @Date: 2022/2/16 15:22
 */
public class SocketServerExam {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9000);

        Socket socket = serverSocket.accept();
        System.out.println("客户端和服务端连接成功");
        //  监听端口，输入流
        InputStream inputStream = socket.getInputStream();
        //  输出流
        OutputStream outputStream = socket.getOutputStream();
        byte[] buf = new byte[ 1024*1024 ];
        int len = inputStream.read(buf);
        //  循环读取socket输入流数据
        while( len!=-1 ) {
            String message = new String(buf,0,len);
            System.out.println("服务器收到客户端消息："+message);
            //  返回给客户端
            outputStream.write("收到，收到".getBytes());
            len = inputStream.read(buf);
        }

        outputStream.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
