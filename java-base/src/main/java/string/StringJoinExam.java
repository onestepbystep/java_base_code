package string;

/**
 * @Author:hmh
 * @Date: 2022/1/25 13:19
 */
public class StringJoinExam {


    public static void main(String[] args) {
        System.out.println(builderString(23));
    }


    /**
     * StringBuilder 线程不安全 继承AbstractStringBuilder
     * @param o
     * @return
     */
    public static String builderString(int o) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(o);
        return stringBuilder.toString();
    }
}
