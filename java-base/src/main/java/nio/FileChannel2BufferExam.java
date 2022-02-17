package nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author:hmh
 * @Date: 2022/2/15 10:06
 */
public class FileChannel2BufferExam {
    private static String path = "G:\\tmp\\word.txt";
    public static void main(String[] args) throws IOException {
        RandomAccessFile rw = new RandomAccessFile(path, "rw");
        FileChannel channel = rw.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);
        //  写数据到buffer
        int read = channel.read(buf);
        while( read!=-1 ){
            //  只执行一次？？？
            System.out.println("read:"+read);

            //  buffer写模式切换到读模式，后就可以读取切换前的所有写数据
            buf.flip();
            //  从buffer读取数据
            while(buf.hasRemaining()){
                System.out.println((char)buf.get());
            }
            //  清空数据，等待数据继续写入buffer
            buf.clear();
            read = channel.read(buf);

        }
        channel.close();

    }


}
