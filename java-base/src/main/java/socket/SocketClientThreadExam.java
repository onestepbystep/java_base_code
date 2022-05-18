package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Author:hmh
 * @Date: 2022/2/16 15:52
 */
public class SocketClientThreadExam {
    public static void main(String[] args) throws IOException {

        for (int i =0;i<10;i++){
            new Thread(){
                @Override
                public void run() {
                    try (
                         Socket socket = new Socket("localhost", 9000) ;
                         InputStream inputStream = socket.getInputStream();
                         OutputStream outputStream = socket.getOutputStream()){

                        //    发送数据
                        outputStream.write(("你好，我是："+Thread.currentThread().getName()).getBytes());
                        byte[] buf = new byte[1024*1024];
                        int len = inputStream.read(buf);
                        if( len!=-1) {
                            String response = new String(buf,0,len);
                            System.out.println(Thread.currentThread().getName()+"客户端收到服务端回复:"+response);
                            len = inputStream.read(buf);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }.start();
        }

    }
}
