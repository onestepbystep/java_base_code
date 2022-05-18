package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Author:hmh
 * @Date: 2022/2/16 17:27
 */
public class NioSocketServer {

    private static CharsetEncoder charsetEecoder = Charset.forName("UTF-8").newEncoder();
    private static CharsetDecoder charsetDecoder = Charset.forName("UTF-8").newDecoder();

    /**
     * buffer
      */
    private static ByteBuffer buffer;
    /**
     *   复用
     */
    private static Selector selector;
    /**
     *   等待队列
     */
    private static LinkedBlockingDeque<SelectionKey> blockingDeque;
    /**
     * 服务
     */
    private static ServerSocketChannel channel;
    private static ExecutorService pools;

    public static void main(String[] args) throws IOException {
        init();
        //  监听socket连接
        listen();
    }

    /**
     * 监听连接端口
     * @throws IOException
     */
    public static void listen() throws IOException {
        selector.select();
        Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
        while(iterator.hasNext()) {
            SelectionKey next = iterator.next();
            //  添加到队列中
            blockingDeque.offer(next);
            //  将渠道从迭代器中删除，长链接
            iterator.remove();
        }
    }

    /**
     * 初始化数据
     * @throws IOException
     */
    public static void init() throws IOException {
        selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //  非阻塞
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(9000));
        // 将channel注册到selector
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);

        blockingDeque = new LinkedBlockingDeque<SelectionKey>(100);
        pools = Executors.newScheduledThreadPool(100);

        while(true) {
            pools.execute(new Worker());
        }


    }


    static class Worker implements Runnable {
        @Override
        public void run() {
            try {
                //  从队列中拉取值
                SelectionKey take = blockingDeque.take();
                //  处理cahnnel
                handleChannel(take);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }

        }

        public void handleChannel(SelectionKey key) throws IOException {
            SocketChannel socket = null;
            // 第一次判断是否可接受
            try {
                if(key.isAcceptable()) {
                    //  得到服务channel
                    ServerSocketChannel server =(ServerSocketChannel)  key.channel();
                    socket =  server.accept();
                    System.out.println("监听到一个socketServer");
                    socket.configureBlocking(false);
                    // 设置可读
                    socket.register(selector,SelectionKey.OP_READ);
                }
                if(key.isReadable()) {
                    System.out.println("监听到一个socketChannel");
                    socket = (SocketChannel) key.channel();
                    int read = socket.read(buffer);
                    if(read!=-1) {
                        buffer.flip();
                        CharBuffer decode = charsetDecoder.decode(buffer);
                        System.out.println("服务端接收到数据："+decode.toString());
                        socket.write(charsetEecoder.encode(CharBuffer.wrap("收到，收到".toCharArray())));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                if(channel!=null) {
                    channel.close();
                }
            }

        }
    }



}
