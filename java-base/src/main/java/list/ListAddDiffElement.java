package list;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:hmh
 * @Date: 2022/1/25 17:04
 */
public class ListAddDiffElement {


    public static void main(String[] args) {
        /**
         * 通过反射，往list字符串中增加int类型元素
         */
        List<String> list = new ArrayList<String>();
        list.add("123");

        System.out.println(list.get(0));

      /*  try {
            list.getClass().getMethod("add",Object.class).invoke(list,2222);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        System.out.println(list.get(0)+"   "+list.get(1));*/


    }
}
