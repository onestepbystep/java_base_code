package nio;
import org.w3c.dom.ranges.Range;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @Author:hmh
 * @Date: 2022/2/16 10:55
 */
public class FileChannelThreadExam {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("G:\\tmp\\thread.txt");
        FileChannel channel = fileOutputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(48);

        channel.lock(0,Integer.MAX_VALUE,false);

        int[] array = new int[]{1,2,3,4,5,6,7,8,9,10};

        Arrays.stream(array).forEach(x->{
            new Thread(){

                @Override
                public void run() {
                    try {
                        channel.write(ByteBuffer.wrap(("hello,world"+x+"\n").getBytes()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        });
   /*     try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        channel.close();
        fileOutputStream.close();


    }
}
