import com.sun.org.apache.xpath.internal.objects.XObject;

import java.util.*;
import java.util.concurrent.Callable;

/**
 * @Author:hmh
 * @Date: 2022/1/17 10:27
 */
public class StringDealExam {

    public static void main(String[] args) {
        String st = "887asdfasdjajdlkjadklfjaklhdfiuh1hhhhhhhlskdjdkla;hh1=32u94uuj";
        System.out.println(StringSubStringMaxLen(st));
        int[] nums = {2,35,67,8,6,0};
        twoSum(nums,8);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" new ");
            }
        }).start();

        new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        }

        new Thread


    }


    /**
     * 计算字符串中不重复的子字符串的最大长度
     * @param str
     * @return
     */
    public static int StringSubStringMaxLen(String str) {

        int n = str.length();
        //  利用滑动窗口
        Set<Character> window = new HashSet<Character>();
        //  滑动窗口左右两个下标
        int left =0 ;int right = 0;
        int maxLen = 1;
        while (right < n) {
            char rigthChar = str.charAt(right);
            //  如果包含，则删除重复的元素的第一个元素
            while  ( window.contains(rigthChar)){
                char leftChar = str.charAt(left);
                window.remove(leftChar);
                left++ ;
            }

            maxLen = Math.max(maxLen,right-left+1);
            window.add(rigthChar);
            //  右边框右移一位
            right++;
        }

        System.out.println(window.toArray());
        return maxLen;
    }


    /**
     * 查找数组中两个数相加等于目标值得小标
     * 例如[2,3,4] 目标=7
     * 返回[1,2]
     * @param nums
     * @param target
     * @return
     */
        public static Object[] twoSum(int[] nums, int target) {
            //  左右两边同时走

            int left =0 ;
            int right = nums.length;
            List list = Arrays.asList(nums);
            List<Integer> rs = new ArrayList<Integer>(2);
            while (left < nums.length) {
                int leftNum = nums[left] ;
                int otherNum = target - leftNum;
                if (list.contains(otherNum)){
                    right = list.indexOf(otherNum);
                    rs.add(left);
                    rs.add(right);
                    break;
                }
                left ++;
            }
            rs.forEach(x -> System.out.println(x));
            return  rs.toArray();
        }

}
