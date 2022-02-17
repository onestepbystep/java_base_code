package array;

import java.util.Arrays;

/**
 * @Author:hmh
 * @Date: 2022/1/25 13:27
 */
public class ArrayCopyExam {


    public static void main(String[] args) {
        Object[] src = new Object[]{2,3,4,5,6,7,7};
        Object[] dest  = copy(src,10);
        System.out.println("length:"+dest.length +" count:"+ Arrays.stream(dest).count());
        Arrays.stream(dest).filter(x -> x!=null).forEach(x -> System.out.print(x+ " "));


    }

    /**
     * 拷贝数组
     * @param src
     * @param size
     * @return
     */
    public static Object[] copy ( Object[] src,int size ) {
        int length = src.length;
        int destSize =  length;
        //  扩充数组长度
        if (destSize < size ) {
            //  加号优先级高于位运算符
            destSize = (destSize <<1) +2;
            if (destSize < size  ) {
                destSize = size;
            }
        }
        Object[] dest = new Object[destSize];
        //  本地方法
        System.arraycopy(src,0,dest,0,length);
        return dest;
    }
}
