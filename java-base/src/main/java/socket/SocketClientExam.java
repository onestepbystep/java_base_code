package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Author:hmh
 * @Date: 2022/2/16 15:31
 */
public class SocketClientExam {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9000);

        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        //  向服务端写入数据
        outputStream.write("您好，我是客户端".getBytes());

        //  从服务端收到数据
        byte[] buf = new byte[1024*1024];
        int len = inputStream.read(buf);
        while(len!=-1) {
            String message = new String(buf,0,len );
            System.out.println("客户端收到服务器信息："+message);
            len = inputStream.read(buf);
        }
        inputStream.close();
        outputStream.close();
        socket.close();
    }


}
