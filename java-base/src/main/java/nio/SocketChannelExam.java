package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Author:hmh
 * @Date: 2022/2/15 15:54
 */
public class SocketChannelExam {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        //  非阻塞
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("http://jenkov.com",80));
        // 判断连接是否完成
        if( socketChannel.finishConnect()) {
            ByteBuffer buffer = ByteBuffer.allocate(48);
            //  channel写入到buffer
            int read = socketChannel.read(buffer);
            //  channel数据还没有写完
            while(read!=-1) {
                //  切换到读模式
                buffer.flip();
                while(buffer.hasRemaining()){
                    System.out.println("buffer read:"+(char)buffer.get());
                }
                buffer.clear();
                read = socketChannel.read(buffer);
            }

        }
        socketChannel.close();


    }
}
