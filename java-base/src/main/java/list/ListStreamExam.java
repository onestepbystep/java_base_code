package list;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:hmh
 * @Date: 2022/2/11 14:33
 */
public class ListStreamExam {

    public static void main(String[] args) {


        List<Integer> integers = Arrays.asList(1, 23, 4, 5);

        //  Collector.joining 元素必须为字符串
        String mergeStr = integers.stream().filter(x -> x.intValue() > 3)
                .map(i -> String.valueOf(i)).collect(Collectors.joining(", "));
        System.out.println("jdk1.8stream，collectors："+mergeStr);
    }
}
