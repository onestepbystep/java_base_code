package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @Author:hmh
 * @Date: 2022/2/16 16:04
 */
public class SocketSeverThreadExam {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9001);
        while(true) {
            Worker worker = new Worker(serverSocket.accept());
            worker.start();
        }


    }

    static class Worker extends Thread{
        private Socket socket;

        Worker(Socket socket){
           this.socket = socket;
        }
        @Override
        public void run() {
            try(
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream()
            ) {
                byte[] buf = new byte[1024*1034];
                int len = inputStream.read(buf);
                if(len !=-1) {
                    String request = new String(buf,0,len);
                    System.out.println("服务端收到客户端消息："+request);
                    outputStream.write("收到，收到".getBytes());
                    len = inputStream.read(buf);
                }
                socket.close();
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
