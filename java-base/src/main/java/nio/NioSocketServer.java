package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Author:hmh
 * @Date: 2022/2/16 17:27
 */
public class NioSocketServer {

    private static CharsetEncoder charsetEecoder = Charset.forName("UTF-8").newEncoder();
    private static CharsetDecoder charsetDecoder = Charset.forName("UTF-8").newDecoder();

    //  buffer
    private static ByteBuffer buffer;
    //  复用
    private static Selector selector;
    //  等待队列
    private static LinkedBlockingDeque<SelectionKey> blockingDeque;
    //  服务
    private static ServerSocketChannel channel;
    private static ExecutorService pools;

    public static void main(String[] args) throws IOException {
        init();
        //  监听socket连接
        listen();
    }

    //  监听连接端口
    public static void listen() throws IOException {
        selector.select();
        Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
        while(iterator.hasNext()) {
            SelectionKey next = iterator.next();
            blockingDeque.offer(next);
            iterator.remove();
        }

    }

    public static void init() throws IOException {
        selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //  非阻塞
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(9000));
        // 将channel注册到selector
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);

        blockingDeque = new LinkedBlockingDeque<SelectionKey>(100);

        while(true) {
            
        }


    }




}
