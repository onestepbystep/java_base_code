package nio;

import com.sun.xml.internal.fastinfoset.util.ValueArrayResourceException;
import sun.net.NetworkServer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author:hmh
 * @Date: 2022/2/15 14:29
 */
public class SelectorExam {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        //  初始化channel
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        //  channel注册到selector
        int insertKey = SelectionKey.OP_READ;
        SelectionKey register = socketChannel.register(selector, insertKey);

        while(true){

            int select = selector.select();
            if( select== 0) continue;
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while( iterator.hasNext()) {

                SelectionKey next = iterator.next();
                if ( next.isAcceptable()){
                    System.out.println("可接受的");
                } else if (next.isConnectable()) {
                    System.out.println("可连接的");
                } else if (next.isReadable()) {
                    System.out.println("可读的");
                }  else if (next.isWritable())  {
                    System.out.println("可写的");
                }
                iterator.remove();
            }

        }

    }
}
