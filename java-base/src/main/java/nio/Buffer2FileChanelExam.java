package nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.MalformedParametersException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

/**
 * @Author:hmh
 * @Date: 2022/2/15 15:39
 */
public class Buffer2FileChanelExam {
    private static String path = "G:\\tmp\\word1.txt";

    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(path,"rw");
        FileChannel channel = randomAccessFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(48);
        String metaString = "New String write to channel "+System.currentTimeMillis();
        // buffer 清除以便写入
        buffer.clear();
        //  buffer put
        buffer.put(metaString.getBytes("utf-8"));
        //  从写切换到读
        buffer.flip();
        //  buffer数据写到channel
        channel.write(buffer);
        // 关闭channel
        channel.close();
    }
}
