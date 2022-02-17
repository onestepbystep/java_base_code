package nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @Author:hmh
 * @Date: 2022/2/15 12:39
 */
public class FileChanel2ChannelExam {
    private static String path = "G:\\tmp\\word.txt";
    private static String path1 = "G:\\tmp\\otherword.txt";

    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(path,"rw");
        FileChannel fromChannel = randomAccessFile.getChannel();

        RandomAccessFile randomAccessFile1 = new RandomAccessFile(path1,"rw");
        FileChannel toChannel = randomAccessFile1.getChannel();

//        toChannel.transferFrom(fromChannel,0,fromChannel.size());
        fromChannel.transferTo(0,fromChannel.size(),toChannel);
    }
}
